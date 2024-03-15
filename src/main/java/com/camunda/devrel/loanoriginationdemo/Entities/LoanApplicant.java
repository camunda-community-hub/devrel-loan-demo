package com.camunda.devrel.loanoriginationdemo.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "LoanApplicants")
public class LoanApplicant {
    @Id
    @GeneratedValue
    private Long id;

    private String givenName;

    private String surname;

    private String address;

    private String city;

    private String stateOrProvince;

    private String country;

    private String postalCode;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<LoanApplication> applications;

    public LoanApplicant(String givenName, String surname, String address, String city, String stateOrProvince, String country, String postalCode) {
        this.givenName = givenName;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
        this.postalCode = postalCode;
    }

    public LoanApplicant() {

    }

    public Long getId() {
        return id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Set<LoanApplication> getApplications() {
        return applications;
    }

    public void setApplications(Set<LoanApplication> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "LoanApplicant{" +
                "id=" + id +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", applications=" + applications +
                '}';
    }
}
