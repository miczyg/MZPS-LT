package com.mzps.web.tourneys;


import com.mzps.model.MatchResult;
import com.mzps.model.Team;

import java.util.List;

public interface MatchResultService {

    MatchResult findById(Long id);

    void saveMatchResult(MatchResult matchResult);

    void updateMatchResult(MatchResult matchResult);

    List<MatchResult> findAllMatchResults();

    List<MatchResult> findAllByLeagueId(Long leagueId);

    List<MatchResult> findAllByTeamsAndLeague(List<Team> matchTeams, Long leagueId);

    void deleteMatchResultById(Long id);

    void deleteAllMatchResults();
}
