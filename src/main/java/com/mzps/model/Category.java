package com.mzps.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mzps.model.enums.TeamCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Categories")
public class Category {

//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name="Name", nullable=false)
    private TeamCategory categoryName;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Team> teams;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Tourney> tourneys;

    //DO NOT DELETE, hibernate requires default no-arg constructor in this case
    public Category(){}

    public Category(String categoryName) {
        this.categoryName = TeamCategory.valueOf(categoryName);
        this.teams = new ArrayList<>();
        this.tourneys = new ArrayList<>();
    }

    public TeamCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(TeamCategory categoryName) {
        this.categoryName = categoryName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
        //maintaining OneToMany relationship
        if (team.getCategory() != this) {
            team.setCategory(this);
        }
    }

    public boolean removeTeam(Team team) {
        if(teams.remove(team)) {
            team.removeCategory();
            return true;
        }
        return false;
    }

    public List<Tourney> getTourneys() {
        return tourneys;
    }

    public void addTourney(Tourney tourney) {
        this.tourneys.add(tourney);
        //maintaining OneToMany relationship
        if (tourney.getCategory() != this) {
            tourney.setCategory(this);
        }
    }

    public boolean removeTourney(Tourney tourney) {
        if(tourneys.remove(tourney)) {
            tourney.removeCategory();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Category[" +
                "categoryName=" + categoryName +
                ']';
    }
}
