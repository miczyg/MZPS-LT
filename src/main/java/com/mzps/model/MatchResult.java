package com.mzps.model;


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

    @OneToMany(cascade=CascadeType.ALL)
    @Size(min=2, max=2)
    private List<TeamResult> teamResults;

    @NotNull
    private Long leagueId;

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

    public List<TeamResult> getTeamResults() {
        return teamResults;
    }

    public void setTeamResults(List<TeamResult> teamResults) {
        this.teamResults = teamResults;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "id=" + id +
                ", matchTeams=" + matchTeams +
                ", teamResults=" + teamResults +
                ", leagueId=" + leagueId +
                '}';
    }
}
