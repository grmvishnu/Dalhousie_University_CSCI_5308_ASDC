package com.group18.controller;

import com.group18.entity.Category;
import com.group18.entity.dto.CategoryDto;
import com.group18.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/category/")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        return this.categoryService.addCategory(categoryDto);
    }

    @GetMapping(value = "/category/{category_id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("category_id") int category_id){
        return this.categoryService.getCategoryById(category_id);
    }

    @DeleteMapping(value = "/category/{category_id}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable("category_id") int category_id) {
        return this.categoryService.deleteCategory(category_id);
    }

    @PutMapping(value = "/category/")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.updateCategory(categoryDto);
    }

    @GetMapping(value = "/category/budget/{budget_id}")
    public ResponseEntity<List<CategoryDto>> getCategoryByBudgetId(@PathVariable("budget_id") int budget_id){
        return this.categoryService.getCategoryByBudgetId(budget_id);
    }

    @GetMapping(value = "/category/trip/{trip_id}")
    public ResponseEntity<List<CategoryDto>> getCategoryByCategoryId(@PathVariable("trip_id") int trip_id){
        return this.categoryService.getCategoriesByTrip(trip_id);
    }

}