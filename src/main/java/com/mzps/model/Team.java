package com.mzps.model;

import com.mzps.model.enums.TeamCategory;
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
	@Column(name="NAME", nullable=false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY", nullable=false)
	private TeamCategory category;

	@Column(name="COACH", nullable=false)
	private String coach;

    @Column(name="PHONE", nullable=false)
    private String phone;

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

	public TeamCategory getCategory() {
		return category;
	}

	public void setCategory(TeamCategory category) {
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
