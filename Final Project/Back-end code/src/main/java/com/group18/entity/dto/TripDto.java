package com.group18.entity.dto;

import java.util.Date;

public class TripDto {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private String country;
    private String city;
    private Long user_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String accepted;

    @Override
    public String toString() {
        return "TripDto{" +
                "id=" + id +
                returnDate()+ returnUserAccepted()+returnCountryCity()+
                '}';
    }

    private String returnDate(){
        return ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'';
    }

    private String returnUserAccepted(){
        return ", user_id=" + user_id +
                ", accepted='" + accepted + '\'' ;
    }

    private  String returnCountryCity(){
        return ", country='" + country + '\'' +
                ", city='" + city + '\'';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }
}
