package com.group18.controller;

import com.group18.entity.dto.ExpenseDto;
import com.group18.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping(value = "/expense/")
    public ResponseEntity<ExpenseDto> addExpense(@RequestBody ExpenseDto expenseDto) {
        return this.expenseService.addExpense(expenseDto);
    }

    @PutMapping(value = "/expense/")
    public ResponseEntity<ExpenseDto> updateExpense(@RequestBody ExpenseDto expenseDto) {
        return this.expenseService.updateExpense(expenseDto);
    }

    @GetMapping(value = "/expense/category/{category_id}")
    public ResponseEntity<List<ExpenseDto>> getExpenseByCategory(@PathVariable("category_id") int category_id) {
        return this.expenseService.getExpensesByCategoryId(category_id);
    }

    @DeleteMapping(value = "/expense/{expense_id}")
    public void deleteBudget(@PathVariable("expense_id") int expense_id) {
        this.expenseService.deleteExpense(expense_id);
    }

}
