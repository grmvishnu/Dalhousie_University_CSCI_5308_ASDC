package com.group18.service;

import java.util.List;

import com.group18.entity.dto.CategoryDto;
import org.springframework.http.ResponseEntity;

import com.group18.entity.Category;

public interface CategoryService {

	ResponseEntity<CategoryDto> addCategory(CategoryDto categoryDto);

	ResponseEntity<CategoryDto> getCategoryById(int category_id);

	ResponseEntity<CategoryDto> updateCategory(CategoryDto categoryDto);

	ResponseEntity<Integer> deleteCategory(int category_id);

	ResponseEntity<List<CategoryDto>> getCategoryByBudgetId(int budget_id);

	ResponseEntity<List<CategoryDto>> getCategoriesByTrip(int trip_id);
}