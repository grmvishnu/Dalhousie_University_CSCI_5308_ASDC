package com.group18.repository;
import com.group18.entity.Budget;
import com.group18.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BudgetRepository budgetRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
        budgetRepository.deleteAll();
    }

    @Test
    void findById() {
        Category category=new Category();
        category.setId(1);
        category.setName("abcd");
        this.categoryRepository.save(category);
        Category foundCategory=this.categoryRepository.findById(1);
        Assertions.assertThat(foundCategory).isNull();
    }
}