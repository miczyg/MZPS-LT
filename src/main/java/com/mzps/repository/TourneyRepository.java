package com.mzps.repository;

import com.mzps.model.Category;
import com.mzps.model.Tourney;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourneyRepository extends JpaRepository<Tourney, Long> {

    Tourney findByNameAndDate(String name, DateTime date);

    List<Tourney> findByCategory(Category category);
}
