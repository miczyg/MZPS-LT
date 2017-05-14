package com.mzps.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Teams")
public class Team implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(name="Name", unique=true, nullable=false)
	private String name;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Category_ID", nullable=false)
	private Category category;

	@Column(name="Coach", nullable=false)
	private String coach;

    @Column(name="Phone", nullable=false)
    private String phone;

	@Column(name="TotalSeasonPoints", nullable=false)
	private int totalSeasonPoints;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="League_ID")
	private League league;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getCoach() {
        return coach;
    }

	public int getTotalSeasonPoints() {
		return totalSeasonPoints;
	}

	public void setTotalSeasonPoints(int totalSeasonPoints) {
		this.totalSeasonPoints = totalSeasonPoints;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;

		//maintaining ManyToOne relationship
		if (!league.getTeams().contains(this)) {
			league.getTeams().add(this);
		}
	}

	public League removeLeague() {
		League league = this.league;
    	if(league.getTeams().contains(this)) {
			league.getTeams().remove(this);
			this.league = null;
		}

		return league;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Team team = (Team) o;

//		TODO: sth may be messed here
		if (id != null ? !id.equals(team.id) : team.id != null) return false;
		if (name != null ? !name.equals(team.name) : team.name != null) return false;
		return category != null ? category.equals(team.category) : team.category == null;
	}

//	@Override
//	public int hashCode() {
//		int result;
//		result = id != null ? id.hashCode() : 0;
//		result = 31 * result + (name != null ? name.hashCode() : 0);
//		result = 31 * result + (category != null ? category.hashCode() : 0);
//		result = 31 * result + (coach != null ? coach.hashCode() : 0);
//		return result;
//	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", category=" + category
				+ ", coach=" + coach + "]";
	}



}
