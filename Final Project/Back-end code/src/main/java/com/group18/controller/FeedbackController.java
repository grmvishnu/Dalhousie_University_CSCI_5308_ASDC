package com.group18.controller;

import com.group18.entity.dto.FeedbackDto;
import com.group18.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping(value = "/fdb/")
	public ResponseEntity<FeedbackDto> addFeedback(@RequestBody FeedbackDto feedbackDto) {
		return this.feedbackService.addFeedback(feedbackDto);
	}

	@GetMapping(value = "/feedback/{trip_id}")
	public ResponseEntity<List<FeedbackDto>> feedbackByTrip(@PathVariable("trip_id") int trip_id) {
		return this.feedbackService.getFeedbackByTrip(trip_id);
	}

	@PutMapping(value = "/feedbackUpdate/")
	public ResponseEntity<FeedbackDto> updateFeedback(@RequestBody FeedbackDto feedbackDto) {
		return this.feedbackService.updateFeedback(feedbackDto);
	}
}
