package com.mzps.model;

import com.mzps.model.enums.TeamCategory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="Categories")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="Name", nullable=false)
    private TeamCategory categoryName;

    //DO NOT DELETE, hibernate requires default no-arg constructor in this case
    public Category(){}

    public Category(String categoryName) {
        this.categoryName = TeamCategory.valueOf(categoryName);
    }

    public TeamCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(TeamCategory categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category[" +
                "id=" + id +
                ", categoryName=" + categoryName +
                ']';
    }
}
