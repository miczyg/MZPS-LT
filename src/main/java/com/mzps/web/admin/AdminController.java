package com.mzps.web.admin;

import com.mzps.model.League;
import com.mzps.model.Tourney;
import com.mzps.util.CustomErrorType;
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
@RequestMapping("/admin")
public class AdminController {

    private static final String TOURNEY_URI = "/tourney/";
    private static final String LEAGUE_URI = "/league/";

    public static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    TourneyService tourneyService; //Service which will do all data retrieval/manipulation work

    @Autowired
    LeagueService leagueService;

    // -------------------Retrieve All Tourneys---------------------------------------------
    @GetMapping(value = TOURNEY_URI)
    public ResponseEntity<List<Tourney>> listAllTourneys() {
        List<Tourney> tourneys = tourneyService.findAllTourneys();

        if (tourneys.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tourneys, HttpStatus.OK);
    }

    // -------------------Retrieve Single Tourney------------------------------------------

    @GetMapping(value = TOURNEY_URI + "{id}")
    public ResponseEntity<?> getTeam(@PathVariable("id") long id) {
        logger.info("Fetching Tourney with id {}", id);
        Tourney tourney = tourneyService.findById(id);
        if (tourney == null) {
            logger.error("Tourney with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Tourney with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tourney, HttpStatus.OK);
    }

    // -------------------Create a Tourney-------------------------------------------

    @PostMapping(value = TOURNEY_URI)
    public ResponseEntity<?> createTourney(@RequestBody Tourney tourney, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Tourney : {}", tourney);

        if (tourneyService.tourneyExists(tourney)) {
            logger.error("Unable to create. A Tourney with name {} already exist", tourney.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Tourney with name " +
                    tourney.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        tourneyService.saveTourney(tourney);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(tourney.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Delete a Tourney-----------------------------------------

    @DeleteMapping(value = TOURNEY_URI + "{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Tourney with id {}", id);

        Tourney tourney = tourneyService.findById(id);
        if (tourney == null) {
            logger.error("Unable to delete. Tourney with id {} not found.", id);
            return new ResponseEntity(
                    new CustomErrorType("Unable to delete. Tourney with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        tourneyService.deleteTourneyById(id);
        return new ResponseEntity<Tourney>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Update a Tourney ------------------------------------------------

    @PutMapping(value = TOURNEY_URI + "{id}")
    public ResponseEntity<?> updateTourney(@PathVariable("id") long id, @RequestBody Tourney tourney) {
        logger.info("Updating Tourney with id {}", id);

        Tourney currentTourney = tourneyService.findById(id);

        if (currentTourney == null) {
            logger.error("Unable to update. Tourney with id {} not found.", id);
            return new ResponseEntity(
                    new CustomErrorType("Unable to upate. Tourney with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentTourney.setName(tourney.getName());
        currentTourney.setCategory(tourney.getCategory());
        currentTourney.setDate(tourney.getDate());
        currentTourney.setAddress(tourney.getAddress());

        tourneyService.updateTourney(currentTourney);
        return new ResponseEntity<>(tourney, HttpStatus.OK);
    }

    // -------------------Retrieve All Leagues---------------------------------------------
    @GetMapping(value = LEAGUE_URI)
    public ResponseEntity<List<League>> listAllLeagues() {
        List<League> leagues = leagueService.findAllLeagues();

        if (leagues.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(leagues, HttpStatus.OK);
    }

    // -------------------Retrieve Single league------------------------------------------

    @GetMapping(value = LEAGUE_URI + "{id}")
    public ResponseEntity<?> getLeague(@PathVariable("id") long id) {
        logger.info("Fetching League with id {}", id);
        League league = leagueService.findById(id);
        if (league == null) {
            logger.error("League with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("League with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(league, HttpStatus.OK);
    }

    // -------------------Create a League-------------------------------------------

    @PostMapping(value = LEAGUE_URI)
    public ResponseEntity<?> createLeague(@RequestBody League league, UriComponentsBuilder ucBuilder) {
        logger.info("Creating League : {}", league);

        if (leagueService.leagueExists(league)) {
            logger.error("Unable to create. A League with name {} already exist", league.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A League with name " +
                    league.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        leagueService.saveLeague(league);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(league.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Delete a League-----------------------------------------

    @DeleteMapping(value = LEAGUE_URI + "{id}")
    public ResponseEntity<?> deleteLeague(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting League with id {}", id);

        League league = leagueService.findById(id);
        if (league == null) {
            logger.error("Unable to delete. League with id {} not found.", id);
            return new ResponseEntity(
                    new CustomErrorType("Unable to delete. League with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        leagueService.deleteLeagueById(id);
        return new ResponseEntity<League>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Update a Tourney ------------------------------------------------

    @PutMapping(value = LEAGUE_URI + "{id}")
    public ResponseEntity<?> updateLeague(@PathVariable("id") long id, @RequestBody League league) {
        logger.info("Updating League with id {}", id);

        League currentLeague = leagueService.findById(id);

        if (currentLeague == null) {
            logger.error("Unable to update. League with id {} not found.", id);
            return new ResponseEntity(
                    new CustomErrorType("Unable to update. League with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentLeague.setName(league.getName());
        currentLeague.getLeaguePoints().clear();
        currentLeague.getLeaguePoints().addAll(league.getLeaguePoints());

        leagueService.updateLeague(currentLeague);
        return new ResponseEntity<>(league, HttpStatus.OK);
    }

}
