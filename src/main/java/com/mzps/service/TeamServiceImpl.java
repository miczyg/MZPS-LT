package com.mzps.service;

import java.util.List;

import com.mzps.model.Team;
import com.mzps.repositories.TeamRepository;
import com.mzps.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("teamService")
@Transactional
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public Team findById(Long id) {
		return teamRepository.findOne(id);
	}

	public Team findByName(String name) {
		return teamRepository.findByName(name);
	}

	public void saveTeam(Team team) {
		teamRepository.save(team);
	}

	public void updateTeam(Team team){
		saveTeam(team);
	}

	public void deleteTeamById(Long id){
		teamRepository.delete(id);
	}

	public void deleteAllTeams(){
		teamRepository.deleteAll();
	}

	public List<Team> findAllTeams(){
		return teamRepository.findAll();
	}

	public boolean isTeamExist(Team team) {
		return findByName(team.getName()) != null;
	}

}
