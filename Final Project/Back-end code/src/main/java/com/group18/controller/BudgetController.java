package com.group18.controller;

import com.group18.entity.dto.BudgetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group18.entity.Budget;
import com.group18.service.BudgetService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	@PostMapping(value = "/budgetAdd/")
	public ResponseEntity<BudgetDto> addBudget(@RequestBody BudgetDto budgetDto) {
		return this.budgetService.addBudget(budgetDto);
	}

	@PutMapping(value = "/budgetUpdate/")
	public ResponseEntity<BudgetDto> updateBudget(@RequestBody BudgetDto budgetDto) {
		return this.budgetService.updateBudget(budgetDto);
	}

	@GetMapping(value = "/budget/{trip_id}")
	public ResponseEntity<BudgetDto> budgetByTrip(@PathVariable("trip_id") int trip_id) {
		return this.budgetService.getBudgetByTrip(trip_id);
	}
}
