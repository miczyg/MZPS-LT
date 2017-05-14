package com.mzps.web.teams;

import java.util.List;

import com.mzps.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.mzps.util.CustomErrorType;

@RestController
@RequestMapping("/teams")
public class TeamController {

	public static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	TeamService teamService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Teams---------------------------------------------
	@GetMapping(value = "/")
	public ResponseEntity<List<Team>> listAllTeams() {
		List<Team> teams = teamService.findAllTeams();
		if (teams.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
	}

	// -------------------Retrieve Single Team------------------------------------------

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getTeam(@PathVariable("id") long id) {
		logger.info("Fetching Team with id {}", id);
		Team team = teamService.findById(id);
		if (team == null) {
			logger.error("Team with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Team with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Team>(team, HttpStatus.OK);
	}

	// -------------------Create a Team-------------------------------------------

	@PostMapping(value = "/")
	public ResponseEntity<?> createTeam(@RequestBody Team team, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Team : {}", team);

		if (teamService.teamExists(team)) {
			logger.error("Unable to create. A Team with name {} and category {} already exist",
					team.getName(), team.getCategoryName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Team with name " +
			team.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		teamService.saveTeam(team);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(team.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Team ------------------------------------------------

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateTeam(@PathVariable("id") long id, @RequestBody Team team) {
		logger.info("Updating Team with id {}", id);

		Team currentTeam = teamService.findById(id);

		if (currentTeam == null) {
			logger.error("Unable to update. Team with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Team with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentTeam.setName(team.getName());
		currentTeam.setCategory(team.getCategory());
		currentTeam.setCoach(team.getCoach());
		currentTeam.setPhone(team.getPhone());

		teamService.updateTeam(currentTeam);
		return new ResponseEntity<Team>(currentTeam, HttpStatus.OK);
	}

	// ------------------- Delete a Team-----------------------------------------

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteTeam(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Team with id {}", id);

		Team team = teamService.findById(id);
		if (team == null) {
			logger.error("Unable to delete. Team with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Team with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		teamService.deleteTeamById(id);
		return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Teams-----------------------------

	@DeleteMapping(value = "/")
	public ResponseEntity<Team> deleteAllTeams() {
		logger.info("Deleting All Teams");

		teamService.deleteAllTeams();
		return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
	}

}