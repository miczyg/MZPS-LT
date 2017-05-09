package com.mzps.web.admin;

import com.mzps.model.Tourney;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    public static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    TourneyService tourneyService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Tourneys---------------------------------------------
    @GetMapping(value = "/")
    public ResponseEntity<List<Tourney>> listAllMatchResults() {
        List<Tourney> tourneys = tourneyService.findAllTourneys();
        //test
        Tourney tourney = new Tourney();
        tourney.setName("TestTurniej");
        tourney.setDate(DateTime.now());
        tourneys.add(tourney);
        //endtest
        if (tourneys.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }
}
