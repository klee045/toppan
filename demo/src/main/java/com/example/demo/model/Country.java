package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @SequenceGenerator(name = "country_id_seq", sequenceName = "country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_seq")
    @Column(name = "id", columnDefinition = "integer NOT NULL DEFAULT nextval('country_id_seq'::regclass)")
    private int id;

    @Column(name = "country_name", columnDefinition = "TEXT NOT NULL")
    private String countryFullName;

    @Column(name = "country_code", columnDefinition = "TEXT NOT NULL")
    private String countryCode;

    public Country() {
    }

    public Country(int id, String countryFullName, String countryCode) {
        this.id = id;
        this.countryFullName = countryFullName;
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryFullName() {
        return this.countryFullName;
    }

    public void setCountryFullName(String countryFullName) {
        this.countryFullName = countryFullName;
    }
}
