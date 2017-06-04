package com.mzps.repository;

import com.mzps.model.Category;
import com.mzps.model.League;
import com.mzps.model.Tourney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {
    League findById(Long id);

    League findByName(String name);

    List<League> findAllByTourneyIn(Collection<Tourney> tourney);

    List<League> findAllByTourneyId(Long tourney_id);

    List<League> findByTourneyCategory(Category tourney_category);
}
