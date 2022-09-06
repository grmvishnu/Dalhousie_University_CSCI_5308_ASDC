package com.group18.serviceImpl;

import com.group18.entity.Budget;
import com.group18.entity.Category;
import com.group18.entity.dto.CategoryDto;
import com.group18.repository.BudgetRepository;
import com.group18.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BudgetRepository budgetRepository;

    @BeforeEach
    void setUp() {
        categoryServiceImpl=new CategoryServiceImpl(categoryRepository,budgetRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCategory() {
        Budget budget=new Budget();
        budget.setId(1);

        Category category=new Category();
        category.setId(1);
        category.setName("aacd");
        category.setBudget(budget);

        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("acas");
        categoryDto.setBudget_id(1);

        Optional<Budget> budgetOptional=Optional.of(budget);

        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Mockito.when(budgetRepository.findById(categoryDto.getBudget_id())).thenReturn(budgetOptional);
        ResponseEntity<CategoryDto> dto=this.categoryServiceImpl.addCategory(categoryDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getCategoryById() {
        Budget budget=new Budget();
        budget.setId(1);

        Category category=new Category();
        category.setId(1);
        category.setName("aacd");
        category.setBudget(budget);

        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("acas");
        categoryDto.setBudget_id(1);

        Mockito.when(categoryRepository.findById(1)).thenReturn(category);
        ResponseEntity<CategoryDto> dto=this.categoryServiceImpl.getCategoryById(1);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void updateCategory() {
        Budget budget=new Budget();
        budget.setId(1);

        Category category=new Category();
        category.setId(1);
        category.setName("aacd");
        category.setBudget(budget);

        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(87);
        categoryDto.setName("acas");
        categoryDto.setBudget_id(1);

        Optional<Budget> budgetOptional=Optional.of(budget);

        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Mockito.when(categoryRepository.findById(87)).thenReturn(category);
        Mockito.when(budgetRepository.findById(categoryDto.getBudget_id())).thenReturn(budgetOptional);
        ResponseEntity<CategoryDto> dto=this.categoryServiceImpl.updateCategory(categoryDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void getCategoryByBudgetId() {
        Budget budget=new Budget();
        budget.setId(1);

        Category category=new Category();
        category.setId(1);
        category.setName("aacd");
        category.setBudget(budget);
        category.setAmount(1220);

        List<Category> categoryList=new ArrayList<>();
        categoryList.add(category);

        Mockito.when(categoryRepository.findByBudget_Id(1)).thenReturn(categoryList);
        ResponseEntity<List<CategoryDto>> dto=this.categoryServiceImpl.getCategoryByBudgetId(1);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getCategoriesByTrip() {
        Budget budget=new Budget();
        budget.setId(1);

        Category category=new Category();
        category.setId(1);
        category.setName("aacd");
        category.setBudget(budget);

        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("acas");
        categoryDto.setBudget_id(1);

        List<Category> categoryList=new ArrayList<>();
        categoryList.add(category);

        Mockito.when(categoryRepository.findByBudget_Id(1)).thenReturn(categoryList);
        Mockito.when(budgetRepository.findByTrip_Id(1)).thenReturn(budget);
        ResponseEntity<List<CategoryDto>> dto=this.categoryServiceImpl.getCategoriesByTrip(1);
        Assertions.assertThat(dto).isNotNull();
    }
}