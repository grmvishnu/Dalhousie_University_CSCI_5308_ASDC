package com.group18.repository;

import com.group18.entity.Category;
import com.group18.entity.Expense;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        expenseRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void findByCategory_IdTrue() {
        Category category=new Category();
        category.setId(1);
        category.setName("abcd");

        this.categoryRepository.save(category);

        Expense expense=new Expense();
        expense.setId(1);
        expense.setCategory(category);

        this.expenseRepository.save(expense);

        List<Expense> expenses= this.expenseRepository.findByCategory_Id(1);
        assertEquals(1,expenses.size());
    }

}