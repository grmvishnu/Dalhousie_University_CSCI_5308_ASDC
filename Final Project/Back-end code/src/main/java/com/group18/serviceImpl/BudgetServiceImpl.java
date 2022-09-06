package com.group18.serviceImpl;

import com.group18.entity.Category;
import com.group18.entity.Trip;
import com.group18.entity.User;
import com.group18.entity.dto.BudgetDto;
import com.group18.entity.dto.TripDto;
import com.group18.repository.CategoryRepository;
import com.group18.repository.TripRepository;
import com.group18.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group18.entity.Budget;
import com.group18.repository.BudgetRepository;
import com.group18.service.BudgetService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BudgetServiceImpl implements BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TripRepository tripRepository;

	public BudgetServiceImpl(BudgetRepository budgetRepository, CategoryRepository categoryRepository, TripRepository tripRepository) {
		this.budgetRepository = budgetRepository;
		this.categoryRepository = categoryRepository;
		this.tripRepository = tripRepository;
	}

	@Override
	public ResponseEntity<BudgetDto> getBudgetByTrip(int trip_id) {
		Budget budget = this.budgetRepository.findByTrip_Id(trip_id);
		BudgetDto budgetDto = convertToDto(budget);
		return ResponseEntity.status(HttpStatus.OK).body(budgetDto);
	}

	@Override
	public ResponseEntity<BudgetDto> addBudget(BudgetDto budgetDto) {
		Budget budget=convertToEntity(budgetDto);
		this.budgetRepository.save(budget);
		return ResponseEntity.status(HttpStatus.CREATED).body(budgetDto);
	}

	@Override
	public ResponseEntity<BudgetDto> updateBudget(BudgetDto budgetDto) {
		Budget foundBudget=this.budgetRepository.findById(budgetDto.getId()).get();

		Budget budget=convertToEntity(budgetDto);

		foundBudget.setMaxAmount(budget.getMaxAmount());
		foundBudget.setAmountSpent(budget.getAmountSpent());
		foundBudget.setTrip(budget.getTrip());
		foundBudget.setName(budget.getName());

		this.budgetRepository.save(foundBudget);
		return ResponseEntity.status(HttpStatus.CREATED).body(budgetDto);
	}

	private Budget convertToEntity(BudgetDto budgetDto){
		Budget budget=new Budget();

		budget.setAmountSpent(budgetDto.getAmountSpent());
		budget.setMaxAmount(budgetDto.getMaxAmount());
		budget.setName(budgetDto.getName());
		Optional<Trip> trip=this.tripRepository.findById(budgetDto.getTrip_id());
		budget.setTrip(trip.get());

		return budget;
	}

	private BudgetDto convertToDto(Budget budget){
		BudgetDto budgetDto=new BudgetDto();

		budgetDto.setAmountSpent(budget.getAmountSpent());
		budgetDto.setMaxAmount(budget.getMaxAmount());
		budgetDto.setName(budget.getName());
		budgetDto.setTrip_id(budget.getTrip().getId());
		budgetDto.setId(budget.getId());

		return budgetDto;
	}

}
