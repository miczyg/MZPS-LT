package com.mzps.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TEAMS")
public class Team implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@Column(name="CATEGORY", nullable=false)
	private String category;

	@Column(name="SEX", nullable=false)
	private String sex;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Team team = (Team) o;

		if (sex != null ? !sex.equals(team.sex) : team.sex!= null) return false;
		if (id != null ? !id.equals(team.id) : team.id != null) return false;
		if (name != null ? !name.equals(team.name) : team.name != null) return false;
		return category != null ? category.equals(team.category) : team.category == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", category=" + category
				+ ", sex=" + sex + "]";
	}


}
