package com.group18.entity.dto;

public class ExpenseDto {
	private int id;
    private String name;
    private String description;
    private int amount;
    private int category_id;

    

    @Override
	public String toString() {
		return returnIdName()+returnAmountCategoryDescription();
	}

    private String returnIdName(){
        return "ExpenseDto [id=" + id +
                ", name='" + name + '\'' ;
    }

    private String returnAmountCategoryDescription(){
        return
                ", description='" + description + '\'' +", amount=" + amount +
                        ", category_id=" + category_id + "]";
    }

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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
