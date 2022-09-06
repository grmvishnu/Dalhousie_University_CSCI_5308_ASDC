package com.group18.repository;

import com.group18.entity.Trip;
import org.springframework.data.repository.CrudRepository;

import com.group18.entity.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public List<Category> findByBudget_Id(int budget_id);

    public Category deleteById(int category_id);

    public Category findById(int category_id);
}