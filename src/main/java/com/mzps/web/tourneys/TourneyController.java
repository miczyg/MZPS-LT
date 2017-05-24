package com.mzps.web.tourneys;

import com.mzps.model.League;
import com.mzps.model.MatchResult;
import com.mzps.model.Team;
import com.mzps.model.Tourney;
import com.mzps.util.CustomErrorType;
import com.mzps.web.admin.LeagueService;
import com.mzps.web.admin.TourneyService;
import com.mzps.web.teams.TeamController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tourneys")
public class TourneyController {

    public static final Logger logger = LoggerFactory.getLogger(TourneyController.class);

    @Autowired
    TourneyService tourneyService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    MatchResultService matchResultService;

    // -------------------Retrieve All MatchResults---------------------------------------------
    @GetMapping(value = "/")
    public ResponseEntity<List<Tourney>> getTourneys() {
        List<Tourney> tourneys = tourneyService.findAllTourneys();
        if (tourneys.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }

    @GetMapping(value = "/{tourneyId}/{leagueId}")
    public ResponseEntity<League> getLeague(@PathVariable Long leagueId){
        League league = leagueService.findById(leagueId);
        return new ResponseEntity<>(league, HttpStatus.OK);
    }

    @PutMapping(value = "/tourneyId}/{leagueId}/{matchId}")
    public ResponseEntity<MatchResult> getMatchResult(@PathVariable Long matchId){
        MatchResult result = matchResultService.findById(matchId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
