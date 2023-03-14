package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "city", nullable = false, length = 50)
    @NotBlank(message = "Every user must have a City name")
    private String city;

    @Column(name = "street", nullable = false, length = 50)
    @NotBlank(message = "Every user must have a Street name")
    private String street;

    @Column(name = "number", nullable = false, length = 50)
    private Integer streetNumber;


    public Address() {
    }

    public Address(Long id, String city, String street, Integer streetNumber) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }
}
