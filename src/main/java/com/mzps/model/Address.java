package com.mzps.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="Addresses",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "CityName", "StreetName", "HallName" } ) })
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="CityName", nullable=false)
    private String cityName;

    @NotEmpty
    @Column(name="StreetName", nullable=false)
    private String streetName;

    @NotEmpty
    @Column(name="HallName", nullable=false)
    private String hallName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getCityName() != null ? !getCityName().equals(address.getCityName()) : address.getCityName() != null)
            return false;
        if (getStreetName() != null ? !getStreetName().equals(address.getStreetName()) : address.getStreetName() != null)
            return false;
        return getHallName() != null ? getHallName().equals(address.getHallName()) : address.getHallName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCityName() != null ? getCityName().hashCode() : 0;
        result = 31 * result + (getStreetName() != null ? getStreetName().hashCode() : 0);
        result = 31 * result + (getHallName() != null ? getHallName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address[" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", hallName='" + hallName + '\'' +
                ']';
    }
}
