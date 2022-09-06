package com.group18.service;

import java.util.List;

import com.group18.entity.dto.TripDto;
import org.springframework.http.ResponseEntity;

import com.group18.entity.Trip;
import com.group18.entity.User;

public interface TripService {
	ResponseEntity<TripDto> addTrip(TripDto tripDto);

	ResponseEntity<TripDto> getTripById(int id);

	ResponseEntity<List<TripDto>> getTripByCountry(String location);

	ResponseEntity<List<TripDto>> getTripByCity(String location);

	ResponseEntity<TripDto> updateTrip(TripDto tripDto);

	ResponseEntity<List<TripDto>> getTripByUser(String accepted);

	ResponseEntity<List<TripDto>> findByUser_Id(long userId);
}