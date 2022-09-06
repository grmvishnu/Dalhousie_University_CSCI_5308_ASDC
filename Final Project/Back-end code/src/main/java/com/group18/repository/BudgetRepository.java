package com.group18.repository;

import org.springframework.data.repository.CrudRepository;

import com.group18.entity.Budget;

public interface BudgetRepository extends CrudRepository<Budget, Integer> {

	public Budget findByTrip_Id(int trip_id);
}
