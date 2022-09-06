package com.group18.serviceImpl;

import com.group18.entity.Budget;
import com.group18.entity.Category;
import com.group18.entity.Expense;
import com.group18.entity.dto.ExpenseDto;
import com.group18.repository.BudgetRepository;
import com.group18.repository.CategoryRepository;
import com.group18.repository.ExpenseRepository;
import com.group18.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public ResponseEntity<List<ExpenseDto>> getExpensesByCategoryId(int category_id){
        List<Expense> expenses=this.expenseRepository.findByCategory_Id(category_id);
        List<ExpenseDto> expenseDtos=expenses.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(expenseDtos);
    }

    @Override
    public ResponseEntity<ExpenseDto> addExpense(ExpenseDto expenseDto) {
        Expense expense=convertToEntity(expenseDto);
        Expense savedExpense=this.expenseRepository.save(expense);

        Category category=this.categoryRepository.findById(expense.getCategory().getId());
        category.setAmount(category.getAmount()+expenseDto.getAmount());
        this.categoryRepository.save(category);

        Budget budget=budgetRepository.findById(category.getBudget().getId()).get();
        budget.setAmountSpent(budget.getAmountSpent()+expenseDto.getAmount());
        this.budgetRepository.save(budget);

        return ResponseEntity.status(HttpStatus.CREATED).body(expenseDto);
    }

    @Override
    public ResponseEntity<ExpenseDto> updateExpense(ExpenseDto expenseDto) {
    	Expense foundExpense=this.expenseRepository.findById(expenseDto.getId()).get();  
    	
    	Expense expense=convertToEntity(expenseDto);
    	
    	foundExpense.setAmount(expense.getAmount());
    	foundExpense.setDescription(expense.getDescription());
    	foundExpense.setName(expense.getName());
    	foundExpense.setCategory(expense.getCategory());
    	
        Expense updateExpense=this.expenseRepository.save(foundExpense);
       
        
        return ResponseEntity.status(HttpStatus.OK).body(expenseDto);
    }

    @Override
    public void deleteExpense(int expense_id) {
        Expense expense=expenseRepository.findById(expense_id).get();

        this.expenseRepository.deleteById(expense_id);

        Category category=this.categoryRepository.findById(expense.getCategory().getId());
        category.setAmount(category.getAmount()-expense.getAmount());
        this.categoryRepository.save(category);

        Budget budget=budgetRepository.findById(category.getBudget().getId()).get();
        budget.setAmountSpent(budget.getAmountSpent()-expense.getAmount());
        this.budgetRepository.save(budget);
    }

    private ExpenseDto convertToDto(Expense expense){
        ExpenseDto expenseDto=new ExpenseDto();

        expenseDto.setId(expense.getId());
        expenseDto.setName(expense.getName());
        expenseDto.setAmount(expense.getAmount());
        expenseDto.setDescription(expense.getDescription());
        expenseDto.setCategory_id(expense.getCategory().getId());

        return expenseDto;
    }

    private Expense convertToEntity(ExpenseDto expenseDto){
        Expense expense=new Expense();

        expense.setId(expenseDto.getId());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setName(expenseDto.getName());
        Optional<Category> category= Optional.ofNullable(this.categoryRepository.findById(expenseDto.getCategory_id()));
        expense.setCategory(category.get());
        return expense;
    }

}
