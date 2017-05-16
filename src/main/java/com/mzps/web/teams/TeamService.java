package com.mzps.web.teams;




import com.mzps.model.Category;
import com.mzps.model.Team;

import java.util.List;

public interface TeamService {
	
	Team findById(Long id);

	List<Team> findByName(String name);

	List<Team> findByCategory(String category);

	Team findByNameAndCategory(String name, Category category);

	void saveTeam(Team team);

	void updateTeam(Team team);

	void deleteTeamById(Long id);

	void deleteAllTeams();

	List<Team> findAllTeams();

	boolean teamExists(Team team);
}