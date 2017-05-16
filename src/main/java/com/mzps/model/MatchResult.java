package com.mzps.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="MatchResults")
public class MatchResult implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany
    @Size(min=2, max=2)
    @JoinColumn(name="Team_ID")
    private List<Team> matchTeams;

    @OneToOne
    @JoinColumn(name="Tourney_ID", nullable = false)
    private Tourney tourney;

    @OneToMany
    @Size(min=2, max=2)
    private List<TeamResult> teamResults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Team> getMatchTeams() {
        return matchTeams;
    }

    public void setMatchTeams(List<Team> matchTeams) {
        this.matchTeams = matchTeams;
    }

    public Tourney getTourney() {
        return tourney;
    }

    public void setTourney(Tourney tourney) {
        this.tourney = tourney;
    }

    public List<TeamResult> getTeamResults() {
        return teamResults;
    }

    public void setTeamResults(List<TeamResult> teamResults) {
        this.teamResults = teamResults;
    }

    @Override
    public String toString() {
        return "TeamResult [id=" + id + ", team1=" + matchTeams.get(0) + ", team2=" + matchTeams.get(1)
                + ", tourney=" + tourney + ", team1Result=" + teamResults.get(0)
                + ", team2Result=" + teamResults.get(1) + "]";
    }
}
