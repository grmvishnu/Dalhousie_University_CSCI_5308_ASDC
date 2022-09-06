package com.group18.controller;

import com.group18.entity.dto.TripDto;
import com.group18.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TripController {
	@Autowired
	private TripService tripService;

	@PostMapping(value = "/trip/")
	public ResponseEntity<TripDto> addTrip(@RequestBody TripDto tripDto) {
		return this.tripService.addTrip(tripDto);
	}

	@GetMapping(value = "/trip/country/{country}")
	public ResponseEntity<List<TripDto>> getTripByCountry(@PathVariable("country") String country) {
		return this.tripService.getTripByCountry(country);
	}

	@GetMapping(value = "/trip/{id}")
	public ResponseEntity<TripDto> getTripById(@PathVariable("id") int id) {
		return this.tripService.getTripById(id);
	}

	@GetMapping(value = "/trip/city/{city}")
	public ResponseEntity<List<TripDto>> getTripByCity(@PathVariable("city") String city) {
		return this.tripService.getTripByCity(city);
	}

	@PutMapping(value = "/trip/")
	public ResponseEntity<TripDto> updateTrip(@RequestBody TripDto tripDto) {
		return this.tripService.updateTrip(tripDto);
	}

	@GetMapping(value = "/trip/accepted/{accepted}")
	public ResponseEntity<List<TripDto>> getTripByAccepted(@PathVariable("accepted") String accepted) {
		return this.tripService.getTripByUser(accepted);
	}

	@GetMapping(value = "/trip/username/{user_id}")
	public ResponseEntity<List<TripDto>> getTripByUser_Id(@PathVariable("user_id") long  user_id) {
		return this.tripService.findByUser_Id(user_id);
	}
}