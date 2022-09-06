package com.group18.service;

import com.group18.entity.dto.FeedbackDto;
import org.springframework.http.ResponseEntity;

import com.group18.entity.Feedback;

import java.util.List;

public interface FeedbackService {
	ResponseEntity<List<FeedbackDto>> getFeedbackByTrip(int trip_id);

	ResponseEntity<FeedbackDto> addFeedback(FeedbackDto feedback);

	ResponseEntity<FeedbackDto> updateFeedback(FeedbackDto feedbackDto);
}
