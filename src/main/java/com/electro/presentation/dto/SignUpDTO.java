package com.electro.presentation.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class SignUpDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private LocalDate birthdate;
    private String job;
    private String country;
    private String city;
    private String streetNo;
    private String streetName;

    // Constructor, getters, and setters
    public SignUpDTO() {
    }

    public SignUpDTO(String name, String email, String password, LocalDate birthdate, String job, String country, String city, String streetNo, String streetName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.job = job;
        this.country = country;
        this.city = city;
        this.streetNo = streetNo;
        this.streetName = streetName;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
