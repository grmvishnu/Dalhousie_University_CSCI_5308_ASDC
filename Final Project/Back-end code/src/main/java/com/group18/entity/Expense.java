package com.group18.entity;

import com.group18.entity.Category;

import javax.persistence.*;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int amount;
    @ManyToOne
    private Category category;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                returnIdName()+
                returnAmountCategoryDescription() +
                '}';
    }

    private String returnIdName(){
        return "id=" + id +
                ", name='" + name + '\'' ;
    }

    private String returnAmountCategoryDescription(){
        return
                ", description='" + description + '\'' +", amount=" + amount +
                ", category=" + category ;
    }

    public Expense(int id, String name, String description, int amount, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public Expense(){

    }
}