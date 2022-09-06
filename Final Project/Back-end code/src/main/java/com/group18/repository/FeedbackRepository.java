package com.group18.repository;

import org.springframework.data.repository.CrudRepository;

import com.group18.entity.Feedback;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {

	List<Feedback> findByTrip_Id(int trip_id);
}