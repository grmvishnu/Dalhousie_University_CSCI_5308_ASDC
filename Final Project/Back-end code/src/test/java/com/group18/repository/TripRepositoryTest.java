package com.group18.repository;

import com.group18.entity.Trip;
import com.group18.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TripRepositoryTest {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        tripRepository.deleteAll();
    }

    @Test
    void findByCityTrue() {
        Trip trip=new Trip();
        trip.setId(23131);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");

        tripRepository.save(trip);
        List<Trip> tripFound=tripRepository.findByCityAndAcceptedIsNotNull("city");

        assertEquals(1,tripFound.size());
    }

    @Test
    void findByCityFalse() {
        Trip trip=new Trip();
        trip.setId(23131);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");

        tripRepository.save(trip);
        List<Trip> tripFound=tripRepository.findByCityAndAcceptedIsNotNull("city");

        assertFalse(tripFound.size()==2);
    }

    @Test
    void findByCityNotNull() {
        Trip trip=new Trip();
        trip.setId(23131);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");

        tripRepository.save(trip);
        List<Trip> tripFound=tripRepository.findByCityAndAcceptedIsNotNull("city");

        Assertions.assertThat(tripFound).isNotNull();
    }

    @Test
    void findByAcceptedTrue() {
        Trip trip=new Trip();
        trip.setId(32112);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("accepted");

        tripRepository.save(trip);
        List<Trip> tripFound=tripRepository.findByAccepted("accepted");

        assertEquals(1,tripFound.size());
    }

    @Test
    void findByAcceptedFalse() {
        Trip trip=new Trip();
        trip.setId(32112);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("accepted");

        tripRepository.save(trip);
        List<Trip> tripFound=tripRepository.findByAccepted("accepted");

        assertFalse(tripFound.size()==2);
    }

    @Test
    void findByAcceptedNotNull() {
        Trip trip=new Trip();
        trip.setId(32112);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("accepted");

        tripRepository.save(trip);
        List<Trip> tripFound=tripRepository.findByAccepted("accepted");

        Assertions.assertThat(tripFound).isNotNull();
    }

    @Test
    void findByCountryTrue() {
        Trip trip=new Trip();
        trip.setId(1);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");

        this.tripRepository.save(trip);
        List<Trip> tripFound=this.tripRepository.findByCountryAndAcceptedIsNotNull("abcd");

        assertEquals(1,tripFound.size());
    }

    @Test
    void findByCountryFalse() {
        Trip trip=new Trip();
        trip.setId(1);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");

        this.tripRepository.save(trip);
        List<Trip> tripFound=this.tripRepository.findByCountryAndAcceptedIsNotNull("abcd");

        assertFalse(tripFound.size()==2);
    }

    @Test
    void findByCountryNotNull() {
        Trip trip=new Trip();
        trip.setId(1);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");

        this.tripRepository.save(trip);
        List<Trip> tripFound=this.tripRepository.findByCountryAndAcceptedIsNotNull("abcd");

        Assertions.assertThat(tripFound).isNotNull();
    }

    @Test
    void findByUserIdNotNull() {
        User user=new User();
        long id=1232;
        user.setId(id);

        this.userRepository.save(user);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");
        trip.setUser(user);

        this.tripRepository.save(trip);
        List<Trip> tripFound=this.tripRepository.findByCountryAndAcceptedIsNotNull("abcd");

        Assertions.assertThat(tripFound).isNotNull();
    }

    @Test
    void findByUserIdTrue() {
        User user=new User();
        long id=1232;
        user.setId(id);

        this.userRepository.save(user);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");
        trip.setUser(user);

        this.tripRepository.save(trip);
        List<Trip> tripFound=this.tripRepository.findByCountryAndAcceptedIsNotNull("abcd");

        assertEquals(1,tripFound.size());
    }

    @Test
    void findByUserIdFalse() {
        User user=new User();
        long id=1232;
        user.setId(id);

        this.userRepository.save(user);

        Trip trip=new Trip();
        trip.setId(1);
        trip.setCountry("abcd");
        trip.setCity("city");
        trip.setAccepted("abcd");
        trip.setUser(user);

        this.tripRepository.save(trip);
        List<Trip> tripFound=this.tripRepository.findByCountryAndAcceptedIsNotNull("abcd");

        assertFalse(tripFound.size()==2);
    }

}