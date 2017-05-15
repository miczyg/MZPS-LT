package com.mzps.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Leagues")
public class League {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="Name", nullable=false)
    private String name;

    @OneToMany(mappedBy = "league")
    private List<Team> teams;

    @OneToMany(mappedBy = "league")
    private List<LeaguePoints> leaguePoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
        //maintaining OneToMany relationship
        if (team.getLeague() != this) {
            team.setLeague(this);
        }
    }

    public boolean removeTeam(Team team) {
        if(teams.remove(team)) {
            team.removeLeague();
            return true;
        }
        return false;
    }

    public List<LeaguePoints> getLeaguePoints() {
        return leaguePoints;
    }

    public void addLeaguePoints(LeaguePoints leaguePoints) {
        this.leaguePoints.add(leaguePoints);
        //maintaining OneToMany relationship
        if(leaguePoints.getLeague() != this) {
            leaguePoints.setLeague(this);
        }
    }

    @Override
    public String toString() {
        return "League[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teams=" + teams +
                ", leaguePoints=" + leaguePoints +
                ']';
    }
}
