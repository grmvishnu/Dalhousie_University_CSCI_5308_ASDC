package com.group18.service;

import com.group18.entity.Expense;
import com.group18.entity.Feedback;
import com.group18.entity.dto.ExpenseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExpenseService {
    ResponseEntity<List<ExpenseDto>> getExpensesByCategoryId(int category_id);

    ResponseEntity<ExpenseDto> addExpense(ExpenseDto expenseDto);

    ResponseEntity<ExpenseDto> updateExpense(ExpenseDto expenseDto);

    void deleteExpense(int expense_id);
}
