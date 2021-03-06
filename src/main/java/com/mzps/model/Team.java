package com.mzps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Teams",
		uniqueConstraints = { @UniqueConstraint( columnNames = { "Name", "Category_ID" } )})
public class Team implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(name="Name", unique=false, nullable=false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Category_ID")
	@JsonBackReference
	private Category category;

	@Column(name="Coach", nullable=false)
	private String coach;

    @Column(name="Phone", nullable=false)
    private String phone;

	@Column(name="TotalSeasonPoints", nullable=false)
	private Integer totalSeasonPoints = 0;

	@ManyToOne
	@JoinColumn(name="League_ID")
	@JsonIgnoreProperties(value = {"teams"}, ignoreUnknown = true)
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

	public String getCategoryName() {
    	return this.category.getCategoryName().name();
	}

	public void setCategory(Category category) {
		this.category = category;

		//maintaining ManyToOne relationship
		if (!category.getTeams().contains(this)) {
			category.getTeams().add(this);
		}
	}

	public Category removeCategory() {
		Category category = this.category;
		if(category.getTeams().contains(this)) {
			category.getTeams().remove(this);
			this.category = null;
		}

		return category;
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

	public Integer getTotalSeasonPoints() {
		return totalSeasonPoints;
	}

	public void setTotalSeasonPoints(int totalSeasonPoints) {
		this.totalSeasonPoints = totalSeasonPoints;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		if (sameAsFormer(league)) return ;

		League oldLeague = this.league;
		this.league = league;

		if(oldLeague!=null){
			oldLeague.removeTeam(this);
		}

		if(league!=null){
			league.addTeam(this);
		}
	}

	private boolean sameAsFormer(League newLeague) {
		return league==null? newLeague == null : league.equals(newLeague);
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
		return "Team{" +
				"id=" + id +
				", name='" + name + '\'' +
				", category=" + category +
				", coach='" + coach + '\'' +
				", phone='" + phone + '\'' +
				", totalSeasonPoints=" + totalSeasonPoints +
				", league=" + league +
				'}';
	}
}
