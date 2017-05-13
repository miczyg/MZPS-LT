package com.mzps.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="MatchResults")
public class MatchResult implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="Team1", nullable = false)
    private Team team1;

    @NotEmpty
    @Column(name="Team2", nullable = false)
    private Team team2;

    @NotEmpty
    @Column(name="Tourney", nullable = false)
    private Tourney tourney;

    @NotNull
    @Column(name="Team1Result", nullable = false)
    private TeamResult team1Result;

    @NotNull
    @Column(name="Team2Result", nullable = false)
    private TeamResult team2Result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Tourney getTourney() {
        return tourney;
    }

    public void setTourney(Tourney tourney) {
        this.tourney = tourney;
    }

    public TeamResult getTeam1Result() {
        return team1Result;
    }

    public void setTeam1Result(TeamResult team1Result) {
        this.team1Result = team1Result;
    }

    public TeamResult getTeam2Result() {
        return team2Result;
    }

    public void setTeam2Result(TeamResult team2Result) {
        this.team2Result = team2Result;
    }

    @Override
    public String toString() {
        return "TeamResult [id=" + id + ", team1=" + team1 + ", team2=" + team2
                + ", tourney=" + tourney + ", team1Result=" + team1Result
                + ", team2Result=" + team2Result + "]";
    }
}
