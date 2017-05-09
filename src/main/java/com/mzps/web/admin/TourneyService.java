package com.mzps.web.admin;

import com.mzps.model.Tourney;

import java.util.List;

public interface TourneyService {

    Tourney findById(Long id);

    void saveTourney(Tourney tourney);

    void updateTourney(Tourney tourney);

    List<Tourney> findAllTourneys();

    void deleteTourneyById(Long id);
}
