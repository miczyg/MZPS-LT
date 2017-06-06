package com.mzps.web.tourneys;

import com.mzps.model.*;
import com.mzps.util.CustomErrorType;
import com.mzps.web.admin.LeagueService;
import com.mzps.web.admin.TourneyService;
import com.mzps.web.teams.TeamController;
import org.apache.xerces.impl.xpath.regex.Match;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Tourney>> getTourneys(@RequestParam("category") String category) {
        List<Tourney> tourneys = tourneyService.findByCategory(category);
        if (tourneys.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }

    @GetMapping(value = "/leagues")
    public ResponseEntity<List<League>> getLeaguesForTourney(@RequestParam("category") String category){
        List<League> leagues = leagueService.findByCategory(category);
        return new ResponseEntity<>(leagues, HttpStatus.OK);
    }

    @GetMapping(value = "/league")
    public ResponseEntity<League> getLeague(@RequestParam("leagueId") Long leagueId){
        League league = leagueService.findById(leagueId);
        return new ResponseEntity<>(league, HttpStatus.OK);
    }

    @GetMapping(value = "/matches")
    public ResponseEntity<List<MatchResult>> getMatchesForLeague(@RequestParam("leagueId") Long leagueId){
        List<MatchResult> leagueMatches = matchResultService.findAllByLeagueId(leagueId);
        return new ResponseEntity<>(leagueMatches, HttpStatus.OK);
    }

    @GetMapping(value = "/match/{matchId}")
    public ResponseEntity<MatchResult> getMatchResult(@PathVariable Long matchId){
        MatchResult result = new MatchResult();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping(value = "match/")
//    public ResponseEntity<?> setMatchResult(@RequestBody MatchResult matchResult,
//                                            UriComponentsBuilder ucBuilder){
//        matchResultService.saveMatchResult(matchResult);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(
//                matchResult.getId()).toUri());
//        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//    }

    @PutMapping(value = "/match/{matchId}")
    public ResponseEntity<?> updateMatchResult(@PathVariable long matchId,
                                               @RequestBody MatchResult matchResult) {
        MatchResult updatedMatch = matchResultService.findById(matchId);
        updatedMatch.setTeamResults(matchResult.getTeamResults());
        matchResultService.updateMatchResult(updatedMatch);
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }
}
