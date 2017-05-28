package com.mzps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Table(name="Leagues")
public class League {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="Name", nullable=false)
    private String name;

    @OneToMany(mappedBy = "league", cascade=CascadeType.MERGE)
    @JsonIgnoreProperties(value = {"league"}, ignoreUnknown = true)
    private List<Team> teams;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="League_Tourney",
            joinColumns=@JoinColumn(name="League_ID"), inverseJoinColumns=@JoinColumn(name="Tourney_ID"))
    private List<Tourney> tourney;

    @OneToMany(mappedBy = "league", cascade=CascadeType.ALL, orphanRemoval=true)
    @OrderBy("place ASC")
    @SortNatural
    @JsonManagedReference
    private List<LeaguePoints> leaguePoints;

    public League() {
        leaguePoints = new ArrayList<>();
    }

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
        if(teams.contains(team)) return;

        this.teams.add(team);
        //maintaining OneToMany relationship
        if (team.getLeague() != this) {
            team.setLeague(this);
        }
    }

    public boolean removeTeam(Team team) {
        if(!teams.contains(team)) return false;

        if(teams.remove(team)) {
            team.setLeague(null);
            return true;
        }
        return false;
    }

    public List<Tourney> getTourney() {
        return tourney;
    }

    public void setTourney(List<Tourney> tourney) {
        this.tourney = tourney;
    }

    public void addTourney(Tourney tourney) { this.tourney.add(tourney); }

    public List<LeaguePoints> getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(List<LeaguePoints> leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    //working algorithm, but not necessary, clear() and addAll() on list has the same effect
    @Deprecated
    public void updateLeaguePoints(List<LeaguePoints> newLeaguePoints) {

        newLeaguePoints.sort((o1, o2) -> o1.getPlace() > o2.getPlace() ? 1 : -1);

        int smallestLeaguePointsSize =
                newLeaguePoints.size() <= leaguePoints.size() ? newLeaguePoints.size() : leaguePoints.size();

        IntStream.range(0, smallestLeaguePointsSize).forEach(idx -> {
            LeaguePoints oldLeaguePoint = leaguePoints.get(idx);
            LeaguePoints newLeaguePoint = newLeaguePoints.get(idx);
            if (oldLeaguePoint.getPlace().equals(newLeaguePoint.getPlace())) {
                oldLeaguePoint.setPoints(newLeaguePoint.getPoints());
            }
        });

        int remainingElementsSize;
        if(newLeaguePoints.size() > leaguePoints.size()) {
            remainingElementsSize = newLeaguePoints.size();
            IntStream.range(smallestLeaguePointsSize, remainingElementsSize).forEach(idx -> {
                addLeaguePoints(newLeaguePoints.get(idx));
            });
        }
        else if(newLeaguePoints.size() < leaguePoints.size()) {
            remainingElementsSize = leaguePoints.size()-1;
            for(int idx = remainingElementsSize; idx >= smallestLeaguePointsSize; idx--) {
                removeLeaguePoints(leaguePoints.get(idx));
            }
        }

    }

    public void addLeaguePoints(LeaguePoints leaguePoints) {
        this.leaguePoints.add(leaguePoints);
        //maintaining OneToMany relationship
        if(leaguePoints.getLeague() != this) {
            leaguePoints.setLeague(this);
        }
    }

    public boolean removeLeaguePoints(LeaguePoints leaguePoints) {
        if(this.leaguePoints.remove(leaguePoints)) {
            leaguePoints.removeLeague();
            return true;
        }
        return false;
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
