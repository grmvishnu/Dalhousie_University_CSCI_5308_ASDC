package com.group18.serviceImpl;

import com.group18.entity.Budget;
import com.group18.entity.Category;
import com.group18.entity.Expense;
import com.group18.entity.Trip;
import com.group18.entity.dto.CategoryDto;
import com.group18.entity.dto.ExpenseDto;
import com.group18.repository.BudgetRepository;
import com.group18.repository.CategoryRepository;
import com.group18.repository.ExpenseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseServiceImplTest {
    private ExpenseServiceImpl expenseServiceImpl;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BudgetRepository budgetRepository;

    @BeforeEach
    void setUp() {
        expenseServiceImpl=new ExpenseServiceImpl(expenseRepository,categoryRepository,budgetRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getExpensesByCategoryIdNotNull() {
        Category category=new Category();
        category.setId(1);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("exp");
        expense.setAmount(100);
        expense.setDescription("desc");
        expense.setCategory(category);

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setName("abcd");

        List<Expense> expenses=new ArrayList<>();
        expenses.add(expense);

        Optional<Category> optionalCategory=Optional.of(category);

        Mockito.when(expenseRepository.findByCategory_Id(1)).thenReturn(expenses);
        ResponseEntity<List<ExpenseDto>> dto=expenseServiceImpl.getExpensesByCategoryId(1);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getExpensesByCategoryIdTrue() {
        Category category=new Category();
        category.setId(1);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("exp");
        expense.setAmount(100);
        expense.setDescription("desc");
        expense.setCategory(category);

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setName("abcd");

        List<Expense> expenses=new ArrayList<>();
        expenses.add(expense);

        Optional<Category> optionalCategory=Optional.of(category);

        Mockito.when(expenseRepository.findByCategory_Id(1)).thenReturn(expenses);
        ResponseEntity<List<ExpenseDto>> dto=expenseServiceImpl.getExpensesByCategoryId(1);
        assertTrue(dto.getBody().get(0).getName().equals("exp"));
    }

    @Test
    void getExpensesByCategoryIdFalse() {
        Category category=new Category();
        category.setId(1);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("exp");
        expense.setAmount(100);
        expense.setDescription("desc");
        expense.setCategory(category);

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setName("abcd");

        List<Expense> expenses=new ArrayList<>();
        expenses.add(expense);

        Optional<Category> optionalCategory=Optional.of(category);

        Mockito.when(expenseRepository.findByCategory_Id(1)).thenReturn(expenses);
        ResponseEntity<List<ExpenseDto>> dto=expenseServiceImpl.getExpensesByCategoryId(1);
        assertFalse(dto.getBody().get(0).getName().equals("not matching"));
    }

    @Test
    void addExpenseNotNull() {
        Budget budget=new Budget();
        budget.setId(20);
        budget.setAmountSpent(20);
        budget.setMaxAmount(100);

        Category category=new Category();
        category.setId(1);
        category.setAmount(100);
        category.setBudget(budget);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("acbd");

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setName("acbd");
        expenseDto.setCategory_id(1);
        expenseDto.setAmount(10);

        this.expenseRepository.save(expense);
        Mockito.when(this.expenseRepository.save(expense)).thenReturn(expense);

        Optional<Budget> optionalBudget=Optional.of(budget);
        Mockito.when(categoryRepository.findById(1)).thenReturn(category);
        Mockito.when(budgetRepository.findById(category.getBudget().getId())).thenReturn(optionalBudget);
        ResponseEntity<ExpenseDto> dto=expenseServiceImpl.addExpense(expenseDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void addExpenseTrue() {
        Budget budget=new Budget();
        budget.setId(20);
        budget.setAmountSpent(20);
        budget.setMaxAmount(100);

        Category category=new Category();
        category.setId(1);
        category.setAmount(100);
        category.setBudget(budget);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("acbd");

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setName("acbd");
        expenseDto.setCategory_id(1);
        expenseDto.setAmount(10);

        this.expenseRepository.save(expense);
        Mockito.when(this.expenseRepository.save(expense)).thenReturn(expense);

        Optional<Budget> optionalBudget=Optional.of(budget);
        Mockito.when(categoryRepository.findById(1)).thenReturn(category);
        Mockito.when(budgetRepository.findById(category.getBudget().getId())).thenReturn(optionalBudget);
        ResponseEntity<ExpenseDto> dto=expenseServiceImpl.addExpense(expenseDto);
        assertTrue(dto.getBody().getName().equals("acbd"));
    }

    @Test
    void addExpenseFalse() {
        Budget budget=new Budget();
        budget.setId(20);
        budget.setAmountSpent(20);
        budget.setMaxAmount(100);

        Category category=new Category();
        category.setId(1);
        category.setAmount(100);
        category.setBudget(budget);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("acbd");

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setName("acbd");
        expenseDto.setCategory_id(1);
        expenseDto.setAmount(10);

        this.expenseRepository.save(expense);
        Mockito.when(this.expenseRepository.save(expense)).thenReturn(expense);

        Optional<Budget> optionalBudget=Optional.of(budget);
        Mockito.when(categoryRepository.findById(1)).thenReturn(category);
        Mockito.when(budgetRepository.findById(category.getBudget().getId())).thenReturn(optionalBudget);
        ResponseEntity<ExpenseDto> dto=expenseServiceImpl.addExpense(expenseDto);
        assertFalse(dto.getBody().getName().equals("abcd"));
    }

    @Test
    void updateExpenseNotNull() {
        Category category=new Category();
        category.setId(1);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("acbd");

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setId(8);
        expenseDto.setName("acbd");
        expenseDto.setCategory_id(1);

        Optional<Expense> optionalExpense=Optional.of(expense);

        this.expenseRepository.save(expense);
        Mockito.when(this.expenseRepository.save(expense)).thenReturn(expense);
        Mockito.when(expenseRepository.findById(8)).thenReturn(optionalExpense);
        Mockito.when(categoryRepository.findById(1)).thenReturn(category);
        ResponseEntity<ExpenseDto> dto=expenseServiceImpl.updateExpense(expenseDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void updateExpenseTrue() {
        Category category=new Category();
        category.setId(1);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("acbd");

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setId(8);
        expenseDto.setName("acbd");
        expenseDto.setCategory_id(1);

        Optional<Expense> optionalExpense=Optional.of(expense);

        this.expenseRepository.save(expense);
        Mockito.when(this.expenseRepository.save(expense)).thenReturn(expense);
        Mockito.when(expenseRepository.findById(8)).thenReturn(optionalExpense);
        Mockito.when(categoryRepository.findById(1)).thenReturn(category);
        ResponseEntity<ExpenseDto> dto=expenseServiceImpl.updateExpense(expenseDto);
        assertTrue(dto.getBody().getId()==8);
    }

    @Test
    void updateExpenseFalse() {
        Category category=new Category();
        category.setId(1);
        Expense expense=new Expense();
        expense.setId(2);
        expense.setName("acbd");

        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setId(8);
        expenseDto.setName("acbd");
        expenseDto.setCategory_id(1);

        Optional<Expense> optionalExpense=Optional.of(expense);

        this.expenseRepository.save(expense);
        Mockito.when(this.expenseRepository.save(expense)).thenReturn(expense);
        Mockito.when(expenseRepository.findById(8)).thenReturn(optionalExpense);
        Mockito.when(categoryRepository.findById(1)).thenReturn(category);
        ResponseEntity<ExpenseDto> dto=expenseServiceImpl.updateExpense(expenseDto);
        assertFalse(dto.getBody().getId()!=8);
    }

}