package com.group18.service;

import com.group18.entity.Category;
import com.group18.entity.dto.BudgetDto;
import org.springframework.http.ResponseEntity;

import com.group18.entity.Budget;

import java.util.List;

public interface BudgetService {
	ResponseEntity<BudgetDto> getBudgetByTrip(int trip_id);

	ResponseEntity<BudgetDto> addBudget(BudgetDto budgetDto);
	
	ResponseEntity<BudgetDto> updateBudget(BudgetDto budgetDto);
}
