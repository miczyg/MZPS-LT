package com.mzps.repository;


import com.mzps.model.MatchResult;
import com.mzps.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {
    List<MatchResult> findByLeagueId(Long leagueId);
    List<MatchResult> findByMatchTeamsAndLeagueId(List<Team> matchTeams, Long leagueId);
}
