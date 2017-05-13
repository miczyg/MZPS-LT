package com.mzps.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Tourneys")
public class Tourney implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="Name", nullable=false)
    private String name;

    @Column(name="Date", nullable=false)
    private DateTime date;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="Category_ID", nullable=false)
    private Category category;

    @OneToOne
    @JoinColumn(name="Address_ID")
    private Address address;

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

    public String getDate() {
        return date.toString("yyyy-MM-dd");
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = DateTime.parse(date, ISODateTimeFormat.dateTime());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Tourney [id=" + id + ", name=" + name + ", date=" + date + "]";
    }
}
