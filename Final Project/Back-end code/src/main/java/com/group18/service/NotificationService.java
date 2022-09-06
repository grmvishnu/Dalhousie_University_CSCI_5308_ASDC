package com.group18.service;

import com.group18.entity.dto.request.TripRequest;

public interface NotificationService {
    String checkBudgetLimit(TripRequest tripRequest);
}
