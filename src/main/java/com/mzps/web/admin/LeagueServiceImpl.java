package com.mzps.web.admin;

import com.mzps.model.League;
import com.mzps.repository.LeagueRepository;
import com.mzps.web.teams.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("leagueService")
@Transactional
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TourneyService tourneyService;



    @Override
    public League findById(Long id) {
        return leagueRepository.findOne(id);
    }

    @Override
    public List<League> findByTourney(Long tourneyId) {
        return leagueRepository.findAllByTourney(tourneyId);
    }

    @Override
    public void saveLeague(League league) {
        league.getLeaguePoints().forEach( leaguePoints -> leaguePoints.setLeague(league));
        if( tourneyService.tourneyExists(league.getTourney()) ){
            league.setTourney(tourneyService.find(league.getTourney()));
        }
        leagueRepository.save(league);
        league.getTeams().forEach( team -> {
            team.setLeague(league);
            teamService.updateTeam(team);
        });
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
        League league = findById(id);
        league.removeAllTeams();
        leagueRepository.delete(id);
    }

    @Override
    public boolean leagueExists(League league) {
        return leagueRepository.findByName(league.getName()) != null;
    }
}
