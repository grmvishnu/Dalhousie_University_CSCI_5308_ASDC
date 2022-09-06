package com.group18.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.group18.entity.dto.TripDto;
import com.group18.repository.UserRepository;
import com.group18.listener.OnTripAcceptEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group18.entity.Trip;
import com.group18.entity.User;
import com.group18.repository.TripRepository;
import com.group18.service.TripService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TripServiceImpl implements TripService {

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository) {
		this.tripRepository = tripRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<TripDto> addTrip(TripDto tripDto) {
		Trip trip=convertToEntity(tripDto);
		Trip savedTrip=this.tripRepository.save(trip);
		return ResponseEntity.status(HttpStatus.CREATED).body(tripDto);
	}

	@Override
	public ResponseEntity<TripDto> getTripById(int id) {
		Trip trip = this.tripRepository.findById(id).get();
		TripDto tripDto=convertToDto(trip);
		return ResponseEntity.status(HttpStatus.OK).body(tripDto);
	}

	@Override
	public ResponseEntity<List<TripDto>> getTripByCountry(String location) {
		List<Trip> trip = this.tripRepository.findByCountryAndAcceptedIsNotNull(location);
		List<TripDto> tripDto=trip.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(tripDto);
	}

	@Override
	public ResponseEntity<List<TripDto>> getTripByCity(String location) {
		List<Trip> trip = this.tripRepository.findByCityAndAcceptedIsNotNull(location);
		List<TripDto> tripDto=trip.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(tripDto);
	}

	@Override
	public ResponseEntity<TripDto> updateTrip(TripDto tripDto) {
		Trip foundTrip=this.tripRepository.findById(tripDto.getId()).get();

		Trip trip=convertToEntity(tripDto);

		if(foundTrip.getAccepted().isEmpty() && !trip.getAccepted().isEmpty()) {
			eventPublisher.publishEvent(new OnTripAcceptEvent(trip));
		}

		foundTrip.setAccepted(trip.getAccepted());
		foundTrip.setCountry(trip.getCountry());
		foundTrip.setStartDate(trip.getStartDate());
		foundTrip.setEndDate(trip.getEndDate());
		foundTrip.setCity(trip.getCity());
		foundTrip.setUser(trip.getUser());

		Trip updateStrip=tripRepository.save(foundTrip);
		return ResponseEntity.status(HttpStatus.OK).body(tripDto);
	}

	@Override
	public ResponseEntity<List<TripDto>> getTripByUser(String accepted) {
		List<Trip> trip = this.tripRepository.findByAccepted(accepted);
		List<TripDto> tripDto=trip.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(tripDto);
	}

	@Override
	public ResponseEntity<List<TripDto>> findByUser_Id(long user_id) {
		List<Trip> findByUser_Id = this.tripRepository.findByUser_IdAndAcceptedIsNotNull(user_id);
		List<TripDto> findByUser_IdDto=findByUser_Id.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(findByUser_IdDto);
	}

	private Trip convertToEntity(TripDto tripDto){
		Trip trip=new Trip();

		trip.setId(tripDto.getId());
		trip.setCountry(tripDto.getCountry());
		trip.setCity(tripDto.getCity());
		trip.setStartDate(tripDto.getStartDate());
		trip.setEndDate(tripDto.getEndDate());
		trip.setName(tripDto.getName());
		trip.setAccepted(tripDto.getAccepted());
		Optional<User> user = userRepository.findById(tripDto.getUser_id());
		if(user.isPresent()){
			trip.setUser(user.get());
		}else {
			throw new RuntimeException("User not found");
		}

		return trip;
	}

	private TripDto convertToDto(Trip trip){
		TripDto tripDto=new TripDto();

		tripDto.setId(trip.getId());
		tripDto.setAccepted(trip.getAccepted());
		tripDto.setCountry(trip.getCountry());
		tripDto.setName(trip.getName());
		tripDto.setCity(trip.getCity());
		tripDto.setEndDate(trip.getEndDate());
		tripDto.setStartDate(trip.getStartDate());
		tripDto.setUser_id(trip.getUser().getId());

		return tripDto;
	}
}