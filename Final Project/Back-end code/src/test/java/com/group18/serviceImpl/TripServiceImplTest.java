package com.group18.serviceImpl;

import com.group18.entity.Trip;
import com.group18.entity.User;
import com.group18.entity.dto.TripDto;
import com.group18.repository.TripRepository;
import com.group18.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TripServiceImplTest {
    private TripServiceImpl tripServiceImpl;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        tripServiceImpl=new TripServiceImpl(tripRepository,userRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addTripNotNull() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);

        TripDto tripDto=new TripDto();
        tripDto.setUser_id(id);

        Optional<User> optionalUser=Optional.of(user);

        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Mockito.when(userRepository.findById(tripDto.getUser_id())).thenReturn(optionalUser);
        ResponseEntity<TripDto> dto=tripServiceImpl.addTrip(tripDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void addTripTrue() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);

        TripDto tripDto=new TripDto();
        tripDto.setUser_id(id);

        Optional<User> optionalUser=Optional.of(user);

        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Mockito.when(userRepository.findById(tripDto.getUser_id())).thenReturn(optionalUser);
        ResponseEntity<TripDto> dto=tripServiceImpl.addTrip(tripDto);
        assertTrue(dto.getBody().getUser_id()==2121);
    }

    @Test
    void addTripFalse() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);

        TripDto tripDto=new TripDto();
        tripDto.setUser_id(id);

        Optional<User> optionalUser=Optional.of(user);

        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Mockito.when(userRepository.findById(tripDto.getUser_id())).thenReturn(optionalUser);
        ResponseEntity<TripDto> dto=tripServiceImpl.addTrip(tripDto);
        assertFalse(dto.getBody().getUser_id()==2421);
    }

    @Test
    void getTripByCountryNotNull() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCountry("country");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByCountryAndAcceptedIsNotNull("abcd")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByCountry("abcd");
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getTripByCountryTrue() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCountry("country");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByCountryAndAcceptedIsNotNull("abcd")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByCountry("abcd");
        assertTrue(dto.getBody().get(0).getId()==1);
    }

    @Test
    void getTripByCountryFalse() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCountry("country");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByCountryAndAcceptedIsNotNull("abcd")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByCountry("abcd");
        assertFalse(dto.getBody().get(0).getId()==3);
    }

    @Test
    void getTripByIdNotNull() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCity("country");

        Mockito.when(tripRepository.findById(1)).thenReturn(Optional.of(trip));
        ResponseEntity<TripDto> dto=tripServiceImpl.getTripById(1);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getTripByIdTrue() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCity("country");

        Mockito.when(tripRepository.findById(1)).thenReturn(Optional.of(trip));
        ResponseEntity<TripDto> dto=tripServiceImpl.getTripById(1);
        assertTrue(dto.getBody().getId()==1);
    }

    @Test
    void getTripByIdFalse() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCity("country");

        Mockito.when(tripRepository.findById(1)).thenReturn(Optional.of(trip));
        ResponseEntity<TripDto> dto=tripServiceImpl.getTripById(1);
        assertFalse(dto.getBody().getId()!=1);
    }

    @Test
    void getTripByCityNotNull() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCity("country");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByCityAndAcceptedIsNotNull("abcd")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByCity("abcd");
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getTripByCityTrue() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCity("country");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByCityAndAcceptedIsNotNull("abcd")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByCity("abcd");
        assertTrue(dto.getBody().get(0).getId()==1);
    }

    @Test
    void getTripByCityFalse() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setCity("country");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByCityAndAcceptedIsNotNull("abcd")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByCity("abcd");
        assertFalse(dto.getBody().get(0).getId()==4);
    }

    @Test
    void updateTripNotNull() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("");

        TripDto tripDto=new TripDto();
        tripDto.setId(4);
        tripDto.setUser_id(id);
        tripDto.setAccepted("");

        Optional<User> optionalUser=Optional.of(user);
        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Mockito.when(tripRepository.findById(4)).thenReturn(optionalTrip);
        Mockito.when(userRepository.findById(tripDto.getUser_id())).thenReturn(optionalUser);
        ResponseEntity<TripDto> dto=tripServiceImpl.updateTrip(tripDto);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void updateTripTrue() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("");

        TripDto tripDto=new TripDto();
        tripDto.setId(4);
        tripDto.setUser_id(id);
        tripDto.setAccepted("");

        Optional<User> optionalUser=Optional.of(user);
        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Mockito.when(tripRepository.findById(4)).thenReturn(optionalTrip);
        Mockito.when(userRepository.findById(tripDto.getUser_id())).thenReturn(optionalUser);
        ResponseEntity<TripDto> dto=tripServiceImpl.updateTrip(tripDto);
        assertTrue(dto.getBody().getId()==4);
    }

    @Test
    void updateTripFalse() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("");

        TripDto tripDto=new TripDto();
        tripDto.setId(4);
        tripDto.setUser_id(id);
        tripDto.setAccepted("");

        Optional<User> optionalUser=Optional.of(user);
        Optional<Trip> optionalTrip=Optional.of(trip);

        Mockito.when(tripRepository.save(trip)).thenReturn(trip);
        Mockito.when(tripRepository.findById(4)).thenReturn(optionalTrip);
        Mockito.when(userRepository.findById(tripDto.getUser_id())).thenReturn(optionalUser);
        ResponseEntity<TripDto> dto=tripServiceImpl.updateTrip(tripDto);
        assertFalse(dto.getBody().getId()!=4);
    }

    @Test
    void getTripByUserNotNull() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("acc");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByAccepted("acc")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByUser("acc");
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void getTripByUserTrue() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("acc");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByAccepted("acc")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByUser("acc");
        assertTrue(dto.getBody().get(0).getId()==1);
    }

    @Test
    void getTripByUserFalse() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("acc");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByAccepted("acc")).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.getTripByUser("acc");
        assertFalse(dto.getBody().get(0).getId()==4);
    }

    @Test
    void findByUser_IdNotNull() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("acc");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByUser_IdAndAcceptedIsNotNull(id)).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.findByUser_Id(id);
        Assertions.assertThat(dto).isNotNull();
    }

    @Test
    void findByUser_IdTrue() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("acc");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByUser_IdAndAcceptedIsNotNull(id)).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.findByUser_Id(id);
        assertTrue(dto.getBody().get(0).getId()==1);
    }

    @Test
    void findByUser_IdFalse() {
        User user=new User();
        long id=2121;
        user.setId(id);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setUser(user);
        trip.setAccepted("acc");

        List<Trip> trips=new ArrayList<>();
        trips.add(trip);

        Mockito.when(tripRepository.findByUser_IdAndAcceptedIsNotNull(id)).thenReturn(trips);
        ResponseEntity<List<TripDto>> dto=tripServiceImpl.findByUser_Id(id);
        assertFalse(dto.getBody().get(0).getId()==5);
    }
}