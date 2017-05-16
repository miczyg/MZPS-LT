package com.mzps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Category_ID")
    @JsonBackReference
    private Category category;

    @OneToOne(cascade=CascadeType.ALL)
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

    public DateTime getDateObject() { return date;}

    public void setDate(DateTime date) {
        this.date = date;
    }

    //parses ISO format from html form date input
    public void setDate(String date) {
        this.date = DateTime.parse(date, ISODateTimeFormat.dateTime());
    }

    //parses pattern yyyy-MM-dd, from getDate() above
    public void setDateShortFormat(String date) {
        this.date = DateTime.parse(date);
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
        if (!category.getTourneys().contains(this)) {
            category.getTourneys().add(this);
        }
    }

    public Category removeCategory() {
        Category category = this.category;
        if(category.getTourneys().contains(this)) {
            category.getTourneys().remove(this);
            this.category = null;
        }

        return category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if(!address.equals(this.address))
            this.address = address;
    }

    @Override
    public String toString() {
        return "Tourney [id=" + id + ", name=" + name + ", date=" + date +
                ", category=" + category + ", address=" + address +"]";
    }
}
