package com.group18.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private String country;
	private String city;
	private double prepayment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private User user;
	private String accepted;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "Trip{" +
				"id=" + id +returnDate() +returnCountryCity() +
				returnUserAccepted() +
				'}';
	}

	private String returnDate(){
		return ", satrtDate='" + startDate + '\'' +
						", endDate='" + endDate + '\'';
	}

	private String returnUserAccepted(){
		return ", user=" + user +
				", accepted='" + accepted + '\'' ;
	}

	private  String returnCountryCity(){
		return ", country='" + country + '\'' +
				", city='" + city + '\'';
	}

	public Trip(){

	}

	public Trip(int id, String name, String startDate, String endDate, String country, String city, User user, String accepted) {
		this.id = id;
		this.name=name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
		this.city = city;
		this.user = user;
		this.accepted = accepted;
	}

	public double getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(double prepayment) {
		this.prepayment = prepayment;
	}
}