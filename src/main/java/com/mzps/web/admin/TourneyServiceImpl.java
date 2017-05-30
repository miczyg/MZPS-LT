package com.mzps.web.admin;


import com.mzps.model.Address;
import com.mzps.model.Tourney;
import com.mzps.repository.AddressRepository;
import com.mzps.repository.TourneyRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mzps.model.Tourney.DATE_TIME_FORMAT;

@Service("tourneyService")
@Transactional
public class TourneyServiceImpl implements TourneyService {

    @Autowired
    private TourneyRepository tourneyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Tourney findById(Long id) {
        return tourneyRepository.findOne(id);
    }

    @Override
    public Tourney find(Tourney tourney) {
        return tourneyRepository.findByNameAndDate(
                tourney.getName(),
                DateTime.parse(tourney.getDate(), DateTimeFormat.forPattern(DATE_TIME_FORMAT)));
    }

    @Override
    public void saveTourney(Tourney tourney) {
        Address newTourneyAddress = tourney.getAddress();
        Address existingTourneyAddress = addressRepository.findByCityNameAndStreetNameAndHallName(
                newTourneyAddress.getCityName(),
                newTourneyAddress.getStreetName(),
                newTourneyAddress.getHallName());
        if( existingTourneyAddress != null) {
            tourney.setAddress(existingTourneyAddress);
        }
        tourneyRepository.save(tourney);
    }

    @Override
    public void updateTourney(Tourney tourney) {
        saveTourney(tourney);
    }

    @Override
    public List<Tourney> findAllTourneys() {
        return tourneyRepository.findAll();
    }

    @Override
    public void deleteTourneyById(Long id) {
        tourneyRepository.delete(id);
    }

    @Override
    public boolean tourneyExists(Tourney tourney) {
        return find(tourney) != null;
    }
}
