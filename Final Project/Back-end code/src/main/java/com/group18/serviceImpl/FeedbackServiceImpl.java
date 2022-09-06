package com.group18.serviceImpl;

import com.group18.entity.Budget;
import com.group18.entity.Trip;
import com.group18.entity.User;
import com.group18.entity.dto.BudgetDto;
import com.group18.entity.dto.FeedbackDto;
import com.group18.entity.dto.TripDto;
import com.group18.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group18.entity.Feedback;
import com.group18.repository.FeedbackRepository;
import com.group18.service.FeedbackService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	public FeedbackServiceImpl(FeedbackRepository feedbackRepository, TripRepository tripRepository) {
		this.feedbackRepository = feedbackRepository;
		this.tripRepository = tripRepository;
	}

	@Autowired
	private TripRepository tripRepository;

	@Override
	public ResponseEntity<List<FeedbackDto>> getFeedbackByTrip(int trip_id) {
		try {
			List<Feedback> feedback = this.feedbackRepository.findByTrip_Id(trip_id);
			List<FeedbackDto> feedbackDto=feedback.stream().map(this::convertToDto).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(feedbackDto);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public ResponseEntity<FeedbackDto> addFeedback(FeedbackDto feedbackDto) {
		Feedback feedback=convertToEntity(feedbackDto);
		this.feedbackRepository.save(feedback);
		return ResponseEntity.status(HttpStatus.CREATED).body(feedbackDto);
	}

	@Override
	public ResponseEntity<FeedbackDto> updateFeedback(FeedbackDto feedbackDto) {
		Feedback foundFeedback=this.feedbackRepository.findById(feedbackDto.getId()).get();

		Feedback feedback=convertToEntity(feedbackDto);

		foundFeedback.setContent(feedback.getContent());
		foundFeedback.setTrip(feedback.getTrip());

		this.feedbackRepository.save(foundFeedback);
		return ResponseEntity.status(HttpStatus.CREATED).body(feedbackDto);
	}

	private Feedback convertToEntity(FeedbackDto feedbackDto){
		Feedback feedback=new Feedback();

		feedback.setContent(feedbackDto.getContent());
		Optional<Trip> trip=this.tripRepository.findById(feedbackDto.getTrip_id());
		feedback.setTrip(trip.get());
		feedback.setId(feedbackDto.getId());

		return feedback;
	}

	private FeedbackDto convertToDto(Feedback feedback){
		FeedbackDto feedbackDto=new FeedbackDto();

		feedbackDto.setId(feedback.getId());
		feedbackDto.setContent(feedback.getContent());
		feedbackDto.setTrip_id(feedback.getTrip().getId());

		return feedbackDto;
	}
}
