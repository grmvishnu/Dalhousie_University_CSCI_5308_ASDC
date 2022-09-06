package com.group18.entity;

import javax.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int amount;
	@ManyToOne
	private Budget budget;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", amount=" + amount + ", budget=" + budget + "]";
	}

	public Category(int id, String name, int amount, Budget budget) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.budget = budget;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

}