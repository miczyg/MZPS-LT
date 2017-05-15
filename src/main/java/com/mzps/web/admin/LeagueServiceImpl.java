package com.mzps.web.admin;

import com.mzps.model.League;
import com.mzps.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("leagueService")
@Transactional
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;


    @Override
    public League findById(Long id) {
        return leagueRepository.findOne(id);
    }

    @Override
    public void saveLeague(League league) {
        leagueRepository.save(league);
    }

    @Override
    public void updateLeague(League league) {
        saveLeague(league);
    }

    @Override
    public List<League> findAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public void deleteLeagueById(Long id) {
        leagueRepository.delete(id);
    }

    @Override
    public boolean leagueExists(League league) {
        return leagueRepository.findByName(league.getName()) != null;
    }
}
