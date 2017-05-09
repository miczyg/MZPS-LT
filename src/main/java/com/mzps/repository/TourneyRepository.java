package com.mzps.repository;

import com.mzps.model.Tourney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourneyRepository extends JpaRepository<Tourney, Long> {
}
