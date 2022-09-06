package com.group18.serviceImpl;

import com.group18.entity.Budget;
import com.group18.entity.Trip;
import com.group18.entity.dto.BudgetDto;
import com.group18.repository.BudgetRepository;
import com.group18.repository.CategoryRepository;
import com.group18.repository.TripRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BudgetServiceImplTest {
    private BudgetServiceImpl budgetServiceImpl;

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private TripRepository tripRepository;

    @BeforeEach
    void setUp(){
        budgetServiceImpl=new BudgetServiceImpl(budgetRepository,categoryRepository,tripRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBudgetByTripNotNull() {
        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);

        Trip trip=new Trip();
        trip.setId(1);
        budget.setTrip(trip);

        Mockito.when(budgetRepository.findByTrip_Id(2)).thenReturn(budget);
        ResponseEntity<BudgetDto> dto=budgetServiceImpl.getBudgetByTrip(2);

        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getBudgetByTripTrue() {
        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);

        Trip trip=new Trip();
        trip.setId(1);
        budget.setTrip(trip);

        Mockito.when(budgetRepository.findByTrip_Id(2)).thenReturn(budget);
        ResponseEntity<BudgetDto> dto=budgetServiceImpl.getBudgetByTrip(2);

        assertTrue(dto.getBody().getAmountSpent()!=1);
    }

    @Test
    void getBudgetByTripFalse() {
        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);

        Trip trip=new Trip();
        trip.setId(1);
        budget.setTrip(trip);

        Mockito.when(budgetRepository.findByTrip_Id(2)).thenReturn(budget);
        ResponseEntity<BudgetDto> dto=budgetServiceImpl.getBudgetByTrip(2);

        assertFalse(dto.getBody().getAmountSpent()==4);
    }

    @Test
    void addBudgetNotNull() {
        Trip trip=new Trip();
        trip.setId(1);

        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);
        budget.setTrip(trip);

        BudgetDto budgetDto=new BudgetDto();
        budgetDto.setAmountSpent(100);
        budgetDto.setMaxAmount(500);
        budgetDto.setTrip_id(1);

        Optional<Trip> tripOptional=Optional.of(trip);

        Mockito.when(budgetRepository.save(budget)).thenReturn(budget);
        Mockito.when(tripRepository.findById(budgetDto.getTrip_id())).thenReturn(tripOptional);

        ResponseEntity<BudgetDto> dto=budgetServiceImpl.addBudget(budgetDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void addBudgetTrue() {
        Trip trip=new Trip();
        trip.setId(1);

        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);
        budget.setTrip(trip);

        BudgetDto budgetDto=new BudgetDto();
        budgetDto.setAmountSpent(100);
        budgetDto.setMaxAmount(500);
        budgetDto.setTrip_id(1);

        Optional<Trip> tripOptional=Optional.of(trip);

        Mockito.when(budgetRepository.save(budget)).thenReturn(budget);
        Mockito.when(tripRepository.findById(budgetDto.getTrip_id())).thenReturn(tripOptional);

        ResponseEntity<BudgetDto> dto=budgetServiceImpl.addBudget(budgetDto);
        assertTrue(dto.getBody().getMaxAmount()==500);
    }

    @Test
    void addBudgetFalse() {
        Trip trip=new Trip();
        trip.setId(1);

        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);
        budget.setTrip(trip);

        BudgetDto budgetDto=new BudgetDto();
        budgetDto.setAmountSpent(100);
        budgetDto.setMaxAmount(500);
        budgetDto.setTrip_id(1);

        Optional<Trip> tripOptional=Optional.of(trip);

        Mockito.when(budgetRepository.save(budget)).thenReturn(budget);
        Mockito.when(tripRepository.findById(budgetDto.getTrip_id())).thenReturn(tripOptional);

        ResponseEntity<BudgetDto> dto=budgetServiceImpl.addBudget(budgetDto);
        assertFalse(dto.getBody().getMaxAmount()==900);
    }

    @Test
    void updateBudgetNotNull() {
        Trip trip=new Trip();
        trip.setId(1);

        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);
        budget.setTrip(trip);

        BudgetDto budgetDto=new BudgetDto();
        budgetDto.setId(98);
        budgetDto.setAmountSpent(100);
        budgetDto.setMaxAmount(500);
        budgetDto.setTrip_id(1);

        Optional<Trip> tripOptional=Optional.of(trip);
        Optional<Budget> budgetOptional=Optional.of(budget);

        Mockito.when(budgetRepository.save(budget)).thenReturn(budget);
        Mockito.when(tripRepository.findById(budgetDto.getTrip_id())).thenReturn(tripOptional);
        Mockito.when(budgetRepository.findById(98)).thenReturn(budgetOptional);
        ResponseEntity<BudgetDto> dto=budgetServiceImpl.updateBudget(budgetDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void updateBudgetTrue() {
        Trip trip=new Trip();
        trip.setId(1);

        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);
        budget.setTrip(trip);

        BudgetDto budgetDto=new BudgetDto();
        budgetDto.setId(98);
        budgetDto.setAmountSpent(100);
        budgetDto.setMaxAmount(500);
        budgetDto.setTrip_id(1);

        Optional<Trip> tripOptional=Optional.of(trip);
        Optional<Budget> budgetOptional=Optional.of(budget);

        Mockito.when(budgetRepository.save(budget)).thenReturn(budget);
        Mockito.when(tripRepository.findById(budgetDto.getTrip_id())).thenReturn(tripOptional);
        Mockito.when(budgetRepository.findById(98)).thenReturn(budgetOptional);
        ResponseEntity<BudgetDto> dto=budgetServiceImpl.updateBudget(budgetDto);
        assertTrue(dto.getBody().getId()==98);
    }

    @Test
    void updateBudgetFalse() {
        Trip trip=new Trip();
        trip.setId(1);

        Budget budget=new Budget();
        budget.setId(1);
        budget.setAmountSpent(100);
        budget.setMaxAmount(500);
        budget.setTrip(trip);

        BudgetDto budgetDto=new BudgetDto();
        budgetDto.setId(98);
        budgetDto.setAmountSpent(100);
        budgetDto.setMaxAmount(500);
        budgetDto.setTrip_id(1);

        Optional<Trip> tripOptional=Optional.of(trip);
        Optional<Budget> budgetOptional=Optional.of(budget);

        Mockito.when(budgetRepository.save(budget)).thenReturn(budget);
        Mockito.when(tripRepository.findById(budgetDto.getTrip_id())).thenReturn(tripOptional);
        Mockito.when(budgetRepository.findById(98)).thenReturn(budgetOptional);
        ResponseEntity<BudgetDto> dto=budgetServiceImpl.updateBudget(budgetDto);
        assertFalse(dto.getBody().getId()==100);
    }
}