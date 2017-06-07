package com.mzps.web.standings;

import com.mzps.model.Team;
import com.mzps.model.enums.TeamCategory;
import com.mzps.web.teams.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2017-06-03.
 */

@RestController
@RequestMapping("/standings")
public class StandingsController {

    @Autowired
    TeamService teamService;


    @GetMapping(value = "/")
    public ResponseEntity<List<Team>> getByCategory(@RequestParam("category") String category) {
        List<Team> teams = teamService.findByCategory(category);
        teams.sort((team1, team2) -> {
            Integer totalPointsTeam1 = team1.getTotalSeasonPoints();
            Integer totalPointsTeam2 = team2.getTotalSeasonPoints();

            int comparePoints = totalPointsTeam2.compareTo(totalPointsTeam1);

            if(comparePoints != 0) {
                return comparePoints;
            }
            else{
                String leagueNameTeam1 = team1.getLeague().getName();
                String leagueNameTeam2 = team2.getLeague().getName();

                return leagueNameTeam1.compareTo(leagueNameTeam2);
            }
        });
        if (teams.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
    }

}
