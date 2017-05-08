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

    @Override
    public String toString() {
        return "TeamResult [id=" + id + ", team1=" + team1 + ", team2=" + team2
                + ", tourney=" + tourney + ", team1Result=" + team1Result
                + ", team2Result=" + team2Result + "]";
    }
}
