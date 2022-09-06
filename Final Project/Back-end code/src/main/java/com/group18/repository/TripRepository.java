package com.group18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.group18.entity.Trip;
import com.group18.entity.User;

public interface TripRepository extends CrudRepository<Trip, Integer> {
	public List<Trip> findByCountryAndAcceptedIsNotNull(String location);

	public List<Trip> findByCityAndAcceptedIsNotNull(String location);

	public List<Trip> findByAccepted(String accepted);

	public List<Trip> findByUser_IdAndAcceptedIsNotNull(long user_id);
}