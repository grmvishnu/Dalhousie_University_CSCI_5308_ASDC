package com.group18.serviceImpl;

import com.group18.entity.Budget;
import com.group18.entity.User;
import com.group18.entity.dto.request.TripRequest;
import com.group18.repository.BudgetRepository;
import com.group18.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceImplTest {
    private NotificationServiceImpl notificationServiceImpl;

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private UserService userService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private Environment env;

    @BeforeEach
    void setUp() {
        notificationServiceImpl=new NotificationServiceImpl(budgetRepository,userService,mailSender,env);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkBudgetLimitTrue() {
        TripRequest tripRequest=new TripRequest();
        tripRequest.setTripId(1);
        long id=1;
        tripRequest.setUserId(id);

        Budget budget=new Budget();
        budget.setMaxAmount(200);
        budget.setAmountSpent(50);

        User user=new User();
        user.setUsername("abcd");

        Mockito.when(budgetRepository.findByTrip_Id(1)).thenReturn(budget);
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        assertEquals(notificationServiceImpl.checkBudgetLimit(tripRequest),"");
    }

    @Test
    void checkBudgetLimitFalse() {
        TripRequest tripRequest=new TripRequest();
        tripRequest.setTripId(1);
        long id=1;
        tripRequest.setUserId(id);

        Budget budget=new Budget();
        budget.setMaxAmount(100);
        budget.setAmountSpent(50);

        User user=new User();
        user.setUsername("abcd");

        Mockito.when(budgetRepository.findByTrip_Id(1)).thenReturn(budget);
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        assertFalse(notificationServiceImpl.checkBudgetLimit(tripRequest).equals("Budget exceeded"));
    }
    @Test
    void checkBudgetLimitExceededTrue() {
        TripRequest tripRequest=new TripRequest();
        tripRequest.setTripId(1);
        long id=1;
        tripRequest.setUserId(id);

        Budget budget=new Budget();
        budget.setMaxAmount(100);
        budget.setAmountSpent(150);

        User user=new User();
        user.setUsername("abcd");

        Mockito.when(budgetRepository.findByTrip_Id(1)).thenReturn(budget);
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        assertTrue(notificationServiceImpl.checkBudgetLimit(tripRequest).equals("Budget Exceeded"));
    }

    @Test
    void checkBudgetLimitExceededFalse() {
        TripRequest tripRequest=new TripRequest();
        tripRequest.setTripId(1);
        long id=1;
        tripRequest.setUserId(id);

        Budget budget=new Budget();
        budget.setMaxAmount(100);
        budget.setAmountSpent(150);

        User user=new User();
        user.setUsername("abcd");

        Mockito.when(budgetRepository.findByTrip_Id(1)).thenReturn(budget);
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        assertFalse(notificationServiceImpl.checkBudgetLimit(tripRequest).equals("Budget not Exceeded"));
    }

    @Test
    void checkBudgetLimitExceededNotNull() {
        TripRequest tripRequest=new TripRequest();
        tripRequest.setTripId(1);
        long id=1;
        tripRequest.setUserId(id);

        Budget budget=new Budget();
        budget.setMaxAmount(100);
        budget.setAmountSpent(150);

        User user=new User();
        user.setUsername("abcd");

        Mockito.when(budgetRepository.findByTrip_Id(1)).thenReturn(budget);
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        assertTrue(notificationServiceImpl.checkBudgetLimit(tripRequest)!=null);
    }

    @Test
    void checkBudgetLimitHalfUtilization() {
        TripRequest tripRequest=new TripRequest();
        tripRequest.setTripId(1);
        long id=1;
        tripRequest.setUserId(id);

        Budget budget=new Budget();
        budget.setMaxAmount(100);
        budget.setAmountSpent(50);

        User user=new User();
        user.setUsername("abcd");

        Mockito.when(budgetRepository.findByTrip_Id(1)).thenReturn(budget);
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        assertEquals(notificationServiceImpl.checkBudgetLimit(tripRequest),"Budget is at 50% utilization");
    }
}