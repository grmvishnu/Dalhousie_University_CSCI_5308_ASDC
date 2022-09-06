package com.group18.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.group18.entity.Budget;
import com.group18.entity.Trip;
import com.group18.entity.User;
import com.group18.entity.dto.CategoryDto;
import com.group18.entity.dto.TripDto;
import com.group18.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group18.entity.Category;
import com.group18.repository.CategoryRepository;
import com.group18.service.CategoryService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BudgetRepository budgetRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository, BudgetRepository budgetRepository) {
		this.categoryRepository = categoryRepository;
		this.budgetRepository = budgetRepository;
	}

	@Override
	public ResponseEntity<CategoryDto> addCategory(CategoryDto categoryDto) {
		Category category=convertToEntity(categoryDto);
		Category categorySaved=this.categoryRepository.save(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
	}

	@Override
	public ResponseEntity<CategoryDto> getCategoryById(int category_id) {
		Category category=this.categoryRepository.findById(category_id);
		CategoryDto categoryDto=convertToDto(category);
		return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
	}

	@Override
	public ResponseEntity<CategoryDto> updateCategory(CategoryDto categoryDto) {
		Category foundCategory=this.categoryRepository.findById(categoryDto.getId());

		Category category=convertToEntity(categoryDto);

		foundCategory.setAmount(category.getAmount());
		foundCategory.setBudget(category.getBudget());
		foundCategory.setName(category.getName());

		Category category1=this.categoryRepository.save(foundCategory);
		return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
	}

	@Override
	public ResponseEntity<Integer> deleteCategory(int category_id) {
		Category category=this.categoryRepository.deleteById(category_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(category_id);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getCategoryByBudgetId(int budget_id) {
		List<Category> categories= this.categoryRepository.findByBudget_Id(budget_id);
		List<CategoryDto> categoryDtos=categories.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(categoryDtos);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getCategoriesByTrip(int trip_id) {
		Budget budget = this.budgetRepository.findByTrip_Id(trip_id);
		List<Category> categories=this.categoryRepository.findByBudget_Id(budget.getId());
		List<CategoryDto> categoryDtos=categories.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(categoryDtos);
	}

	private Category convertToEntity(CategoryDto categoryDto){
		Category category=new Category();

		category.setId(categoryDto.getId());
		category.setAmount(categoryDto.getAmount());
		category.setName(categoryDto.getName());
		Optional<Budget> budget=this.budgetRepository.findById(categoryDto.getBudget_id());
		category.setBudget(budget.get());

		return category;
	}

	private CategoryDto convertToDto(Category category){
		CategoryDto categoryDto=new CategoryDto();

		categoryDto.setId(category.getId());
		categoryDto.setAmount(category.getAmount());
		categoryDto.setName(category.getName());
		categoryDto.setBudget_id(category.getBudget().getId());

		return categoryDto;
	}

}