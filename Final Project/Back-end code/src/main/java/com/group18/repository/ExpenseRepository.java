package com.group18.repository;

import com.group18.entity.Expense;
import com.group18.entity.Feedback;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
    public List<Expense> findByCategory_Id(int category_id);
}
