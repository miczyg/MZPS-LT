package com.mzps.web.admin;

import com.mzps.model.League;

import java.util.List;

public interface LeagueService {

    League findById(Long id);

    void saveLeague(League league);

    void updateLeague(League league);

    List<League> findAllLeagues();

    void deleteLeagueById(Long id);

    boolean leagueExists(League league);
}
