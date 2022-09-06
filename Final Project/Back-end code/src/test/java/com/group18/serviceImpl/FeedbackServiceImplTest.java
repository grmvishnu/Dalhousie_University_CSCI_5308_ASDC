package com.group18.serviceImpl;

import com.group18.entity.Feedback;
import com.group18.entity.Trip;
import com.group18.entity.dto.FeedbackDto;
import com.group18.repository.FeedbackRepository;
import com.group18.repository.TripRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedbackServiceImplTest {

    @Autowired
    private FeedbackServiceImpl feedbackServiceImpl;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private TripRepository tripRepository;

    @BeforeEach
    void setUp() {
        feedbackServiceImpl=new FeedbackServiceImpl(feedbackRepository,tripRepository);
    }

    @AfterEach
    void tearDown() {
        this.tripRepository.deleteAll();
        this.feedbackRepository.deleteAll();
    }

    @Test
    void getFeedbackByTripNotNull() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setContent("content");
        feedback.setTrip(trip);

        List<Feedback> feedbacks=new ArrayList<>();
        feedbacks.add(feedback);

        feedbackRepository.save(feedback);

        Mockito.when(feedbackRepository.findByTrip_Id(1)).thenReturn(feedbacks);
        ResponseEntity<List<FeedbackDto>> dto=feedbackServiceImpl.getFeedbackByTrip(1);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getFeedbackByTripTrue() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setContent("content");
        feedback.setTrip(trip);

        List<Feedback> feedbacks=new ArrayList<>();
        feedbacks.add(feedback);

        feedbackRepository.save(feedback);

        Mockito.when(feedbackRepository.findByTrip_Id(1)).thenReturn(feedbacks);
        ResponseEntity<List<FeedbackDto>> dto=feedbackServiceImpl.getFeedbackByTrip(1);
        assertEquals(dto.getBody().size(),1);
    }

    @Test
    void getFeedbackByTripFalse() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setContent("content");
        feedback.setTrip(trip);

        List<Feedback> feedbacks=new ArrayList<>();
        feedbacks.add(feedback);

        feedbackRepository.save(feedback);

        Mockito.when(feedbackRepository.findByTrip_Id(1)).thenReturn(feedbacks);
        ResponseEntity<List<FeedbackDto>> dto=feedbackServiceImpl.getFeedbackByTrip(1);
        assertFalse(dto.getBody().size()!=1);
    }

    @Test
    void addFeedbackNotNull() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setContent("content");
        feedback.setTrip(trip);

        FeedbackDto feedbackDto=new FeedbackDto();
        feedbackDto.setContent("content");
        feedbackDto.setTrip_id(feedback.getTrip().getId());

        feedbackRepository.save(feedback);

        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(feedbackRepository.save(feedback)).thenReturn(feedback);
        Mockito.when(tripRepository.findById(feedbackDto.getTrip_id())).thenReturn(optionalTrip);
        ResponseEntity<FeedbackDto> dto=feedbackServiceImpl.addFeedback(feedbackDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void addFeedbackTrue() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setContent("content");
        feedback.setTrip(trip);

        FeedbackDto feedbackDto=new FeedbackDto();
        feedbackDto.setContent("content");
        feedbackDto.setTrip_id(feedback.getTrip().getId());

        feedbackRepository.save(feedback);

        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(feedbackRepository.save(feedback)).thenReturn(feedback);
        Mockito.when(tripRepository.findById(feedbackDto.getTrip_id())).thenReturn(optionalTrip);
        ResponseEntity<FeedbackDto> dto=feedbackServiceImpl.addFeedback(feedbackDto);
        assertTrue(dto.getBody().getTrip_id()==1);
    }

    @Test
    void addFeedbackFalse() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setContent("content");
        feedback.setTrip(trip);

        FeedbackDto feedbackDto=new FeedbackDto();
        feedbackDto.setContent("content");
        feedbackDto.setTrip_id(feedback.getTrip().getId());

        feedbackRepository.save(feedback);

        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(feedbackRepository.save(feedback)).thenReturn(feedback);
        Mockito.when(tripRepository.findById(feedbackDto.getTrip_id())).thenReturn(optionalTrip);
        ResponseEntity<FeedbackDto> dto=feedbackServiceImpl.addFeedback(feedbackDto);
        assertFalse(dto.getBody().getTrip_id()!=1);
    }

    @Test
    void updateFeedbackNotNull() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setId(4);
        feedback.setContent("content");
        feedback.setTrip(trip);

        FeedbackDto feedbackDto=new FeedbackDto();
        feedbackDto.setId(4);
        feedbackDto.setContent("content");
        feedbackDto.setTrip_id(feedback.getTrip().getId());

        feedbackRepository.save(feedback);

        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(feedbackRepository.save(feedback)).thenReturn(feedback);
        Mockito.when(tripRepository.findById(feedbackDto.getTrip_id())).thenReturn(optionalTrip);
        Mockito.when(feedbackRepository.findById(feedbackDto.getId())).thenReturn(Optional.of(feedback));
        ResponseEntity<FeedbackDto> dto=feedbackServiceImpl.updateFeedback(feedbackDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void updateFeedbackTrue() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setId(4);
        feedback.setContent("content");
        feedback.setTrip(trip);

        FeedbackDto feedbackDto=new FeedbackDto();
        feedbackDto.setId(4);
        feedbackDto.setContent("content");
        feedbackDto.setTrip_id(feedback.getTrip().getId());

        feedbackRepository.save(feedback);

        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(feedbackRepository.save(feedback)).thenReturn(feedback);
        Mockito.when(tripRepository.findById(feedbackDto.getTrip_id())).thenReturn(optionalTrip);
        Mockito.when(feedbackRepository.findById(feedbackDto.getId())).thenReturn(Optional.of(feedback));
        ResponseEntity<FeedbackDto> dto=feedbackServiceImpl.updateFeedback(feedbackDto);
        assertTrue(dto.getBody().getTrip_id()==1);
    }

    @Test
    void updateFeedbackFalse() {
        Trip trip=new Trip();
        trip.setId(1);

        tripRepository.save(trip);

        Feedback feedback=new Feedback();
        feedback.setId(4);
        feedback.setContent("content");
        feedback.setTrip(trip);

        FeedbackDto feedbackDto=new FeedbackDto();
        feedbackDto.setId(4);
        feedbackDto.setContent("content");
        feedbackDto.setTrip_id(feedback.getTrip().getId());

        feedbackRepository.save(feedback);

        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(feedbackRepository.save(feedback)).thenReturn(feedback);
        Mockito.when(tripRepository.findById(feedbackDto.getTrip_id())).thenReturn(optionalTrip);
        Mockito.when(feedbackRepository.findById(feedbackDto.getId())).thenReturn(Optional.of(feedback));
        ResponseEntity<FeedbackDto> dto=feedbackServiceImpl.updateFeedback(feedbackDto);
        assertFalse(dto.getBody().getTrip_id()!=1);
    }
}