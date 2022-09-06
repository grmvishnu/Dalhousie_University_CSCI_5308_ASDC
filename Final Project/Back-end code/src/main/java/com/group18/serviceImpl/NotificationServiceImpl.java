package com.group18.serviceImpl;

import com.group18.entity.Budget;
import com.group18.entity.User;
import com.group18.entity.dto.request.TripRequest;
import com.group18.repository.BudgetRepository;
import com.group18.repository.CategoryRepository;
import com.group18.service.NotificationService;
import com.group18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    public NotificationServiceImpl(BudgetRepository budgetRepository, UserService userService, JavaMailSender mailSender, Environment env) {
        this.budgetRepository = budgetRepository;
        this.userService = userService;
        this.mailSender = mailSender;
        this.env = env;
    }

    @Override
    public String checkBudgetLimit(TripRequest tripRequest) {
        Budget budget = budgetRepository.findByTrip_Id(tripRequest.getTripId());
        User user = userService.getUserById(tripRequest.getUserId());
        double maxBudget = budget.getMaxAmount();
        double halfUtilizationBudget = budget.getMaxAmount() * 0.5;
        if (budget.getAmountSpent() >= maxBudget) {
            final SimpleMailMessage email = constructBudgetExceeded(user, tripRequest.getTripId());
            mailSender.send(email);
            return "Budget Exceeded";
        } else if (budget.getAmountSpent() >= halfUtilizationBudget) {
            return "Budget is at 50% utilization";
        } else {
            return "";
        }
    }

    private SimpleMailMessage constructBudgetExceeded(User user, int tripId) {
        final String recipientAddress = user.getUsername();
        final String subject = "Budget Exceeded Notification";
        final String message = "Your budget for trip:" + tripId + " has been exceeded";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
