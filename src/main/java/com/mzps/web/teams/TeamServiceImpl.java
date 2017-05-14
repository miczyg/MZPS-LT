package com.mzps.web.teams;

import java.util.List;

import com.mzps.model.Category;
import com.mzps.model.Team;

import com.mzps.repository.TeamRepository;
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

	public List<Team> findByName(String name) {
		return teamRepository.findByName(name);
	}

	@Override
	public List<Team> findByCategory(String category) {
		return teamRepository.findByCategory(category);
	}

	@Override
	public Team findByNameAndCategory(String name, Category category) {
		return teamRepository.findByNameAndCategory(name, category);
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

	public boolean teamExists(Team team) {

		return findByNameAndCategory(team.getName(), team.getCategory()) != null;
	}

}
