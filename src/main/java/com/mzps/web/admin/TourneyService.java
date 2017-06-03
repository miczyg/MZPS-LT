package com.mzps.web.admin;

import com.mzps.model.Tourney;

import java.util.List;

public interface TourneyService {

    Tourney findById(Long id);

    List<Tourney> findByCategory(String category);

    Tourney find(Tourney tourney);

    void saveTourney(Tourney tourney);

    void updateTourney(Tourney tourney);

    List<Tourney> findAllTourneys();

    void deleteTourneyById(Long id);

    boolean tourneyExists(Tourney tourney);
}
