package com.mzps.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="TeamResults")
public class TeamResult implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="Set1Points", nullable = false)
    private int set1Points;

    @Column(name="Set2Points", nullable = false)
    private int set2Points;

    @Column(name="Set3Points")
    private int set3Points;

    public int getSet1Points() {
        return set1Points;
    }

    public void setSet1Points(int set1Points) {
        this.set1Points = set1Points;
    }

    public int getSet2Points() {
        return set2Points;
    }

    public void setSet2Points(int set2Points) {
        this.set2Points = set2Points;
    }

    public int getSet3Points() {
        return set3Points;
    }

    public void setSet3Points(int set3Points) {
        this.set3Points = set3Points;
    }

    @Override
    public String toString() {
        return "TeamResult [id=" + id + ", set1Points=" + set1Points  + ", set2Points=" + set2Points
                + ", set3Points=" + set3Points + "]";
    }

}
