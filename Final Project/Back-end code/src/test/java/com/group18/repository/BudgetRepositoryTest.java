package com.group18.repository;

import com.group18.entity.Budget;
import com.group18.entity.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class BudgetRepositoryTest {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private TripRepository tripRepository;

    @Test
    void findByTrip_IdNull() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);
        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setAmountSpent(10);
        budget.setTrip(trip);

        this.budgetRepository.save(budget);

        Budget budgetFound=this.budgetRepository.findByTrip_Id(1);
        Assertions.assertThat(budgetFound).isNull();
    }

    @Test
    void findByTrip_IdTrue() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);
        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setAmountSpent(10);
        budget.setTrip(trip);

        this.budgetRepository.save(budget);

        Budget budgetFound=this.budgetRepository.findByTrip_Id(1);
        assertTrue(budgetFound==null);
    }

    @Test
    void findByTrip_IdFalse() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);
        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setAmountSpent(10);
        budget.setTrip(trip);

        this.budgetRepository.save(budget);

        Budget budgetFound=this.budgetRepository.findByTrip_Id(1);
        assertFalse(budgetFound==null);
    }

    @AfterEach
    void tearDown(){
        budgetRepository.deleteAll();
        tripRepository.deleteAll();
    }
}