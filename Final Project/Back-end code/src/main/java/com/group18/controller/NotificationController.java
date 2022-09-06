package com.group18.controller;

import com.group18.entity.dto.request.TripRequest;
import com.group18.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/budget")
    public ResponseEntity<String> checkBudgetLimit(@RequestBody TripRequest tripRequest) {
        String response = notificationService.checkBudgetLimit(tripRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
