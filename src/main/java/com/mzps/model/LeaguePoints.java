package com.mzps.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="LeaguePoints")
public class LeaguePoints {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="Place", nullable=false)
    private int place;

    @NotEmpty
    @Column(name="Points", nullable=false)
    private int points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="League_ID")
    private League league;

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPoints() {
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
}
