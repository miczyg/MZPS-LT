package com.mzps.web.tourneys;

import com.mzps.model.MatchResult;
import com.mzps.model.Team;
import com.mzps.util.CustomErrorType;
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
    MatchResultService matchResultService;

    // -------------------Retrieve All MatchResults---------------------------------------------
    @GetMapping(value = "/")
    public ResponseEntity<List<MatchResult>> listAllMatchResults() {
        List<MatchResult> matchResults = matchResultService.findAllMatchResults();
        if (matchResults.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(matchResults, HttpStatus.OK);
    }
}
