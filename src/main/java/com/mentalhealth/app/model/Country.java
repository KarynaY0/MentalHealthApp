package com.mentalhealth.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name", nullable = false, unique = true, length = 100)
    private String countryName;

    public Country() {}

    public Integer getCountryId() { return countryId; }
    public void setCountryId(Integer countryId) { this.countryId = countryId; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }
}