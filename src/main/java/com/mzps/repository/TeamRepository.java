package com.mzps.repository;


import com.mzps.model.Category;
import com.mzps.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByName(String name);
    List<Team> findByCategory(Category category);
    Team findByNameAndCategory(String name, Category category);

}
