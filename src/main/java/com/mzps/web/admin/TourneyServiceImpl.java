package com.mzps.web.admin;


import com.mzps.model.Tourney;
import com.mzps.repository.MatchResultRepository;
import com.mzps.repository.TourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tourneyService")
@Transactional
public class TourneyServiceImpl implements TourneyService {

    @Autowired
    private TourneyRepository tourneyRepository;


    @Override
    public Tourney findById(Long id) {
        return tourneyRepository.findOne(id);
    }

    @Override
    public void saveTourney(Tourney tourney) {
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
}
