package com.mzps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;

@Entity
@Table(name="LeaguePoints")
public class LeaguePoints {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @Column(name="Place", nullable=false)
    private Integer place;


    @Column(name="Points", nullable=false)
    private Integer points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="League_ID")
    @JsonBackReference
    private League league;

    public Integer getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;

        //maintaining ManyToOne relationship
        if (!league.getLeaguePoints().contains(this)) {
            league.getLeaguePoints().add(this);
        }
    }

    public League removeLeague() {
        League league = this.league;
        if(league.getLeaguePoints().contains(this)) {
            league.getLeaguePoints().remove(this);
            this.league = null;
        }

        return league;
    }

    @Override
    public String toString() {
        return "LeaguePoints[" +
                "id=" + id +
                ", place=" + place +
                ", points=" + points +
                ']';
    }
}
