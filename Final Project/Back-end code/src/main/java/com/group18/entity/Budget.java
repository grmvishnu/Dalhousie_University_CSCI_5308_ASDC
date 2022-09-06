package com.group18.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int amountSpent;
	private int maxAmount;
	@OneToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private Trip trip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(int amountSpent) {
		this.amountSpent = amountSpent;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", name=" + name +", amountSpent=" + amountSpent + ", maxAmount=" + maxAmount + ", trip=" + trip
				+ "]";
	}

	public Budget(int id, String name,int amountSpent, int maxAmount, Trip trip) {
		super();
		this.id = id;
		this.name=name;
		this.amountSpent = amountSpent;
		this.maxAmount = maxAmount;
		this.trip = trip;
	}

	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Budget budget = (Budget) o;
		return checkIdAmount(budget) && checkMaxAmountTrip(budget);
	}

	private boolean checkIdAmount(Budget budget){
		return id == budget.id && amountSpent == budget.amountSpent;
	}

	private boolean checkMaxAmountTrip(Budget budget){
		return maxAmount == budget.maxAmount && Objects.equals(trip, budget.trip);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amountSpent, maxAmount, trip);
	}
}