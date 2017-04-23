package com.mzps.controllers;

import java.util.List;

import com.mzps.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mzps.service.TeamService;
import com.mzps.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	TeamService teamService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/team/", method = RequestMethod.GET)
	public ResponseEntity<List<Team>> listAllUsers() {
		List<Team> teams = teamService.findAllTeams();
		if (teams.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
	}

	// -------------------Retrieve Single Team------------------------------------------

	@RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
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

	@RequestMapping(value = "/team/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Team team, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Team : {}", team);

		if (teamService.isTeamExist(team)) {
			logger.error("Unable to create. A Team with name {} already exist", team.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Team with name " +
			team.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		teamService.saveTeam(team);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/team/{id}").buildAndExpand(team.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Team ------------------------------------------------

	@RequestMapping(value = "/team/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Team team) {
		logger.info("Updating Team with id {}", id);

		Team currentTeam = teamService.findById(id);

		if (currentTeam == null) {
			logger.error("Unable to update. Team with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Team with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentTeam.setName(team.getName());
		currentTeam.setCategory(team.getCategory());
		currentTeam.setSex(team.getSex());

		teamService.updateTeam(currentTeam);
		return new ResponseEntity<Team>(currentTeam, HttpStatus.OK);
	}

	// ------------------- Delete a Team-----------------------------------------

	@RequestMapping(value = "/team/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
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

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/team/", method = RequestMethod.DELETE)
	public ResponseEntity<Team> deleteAllUsers() {
		logger.info("Deleting All Users");

		teamService.deleteAllTeams();
		return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
	}

}