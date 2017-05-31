package com.mzps.repository;

import com.mzps.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {

    League findByName(String name);

    List<League> findAllByTourney(Long tourneyId);
}
