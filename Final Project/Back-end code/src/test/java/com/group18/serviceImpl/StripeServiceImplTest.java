package com.group18.serviceImpl;

import com.group18.entity.Trip;
import com.group18.entity.dto.ChargeRequest;
import com.group18.repository.TripRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class StripeServiceImplTest {
    @Autowired
    private StripeServiceImpl stripeServiceImpl;

    @Mock
    private TripRepository tripRepository;

    @BeforeEach
    void setUp() {
        stripeServiceImpl=new StripeServiceImpl(tripRepository);
    }

    @Test
    void chargeNotNull() throws StripeException {
        Trip trip=new Trip();
        trip.setId(1);

        ChargeRequest chargeRequest=new ChargeRequest();
        chargeRequest.setAmount(1000);
        chargeRequest.setDescription("desc");
        chargeRequest.setStripeToken("tok_visa");
        chargeRequest.setCurrency("CAD");
        chargeRequest.setTrip(1);

        Mockito.when( tripRepository.findById(1)).thenReturn(Optional.of(trip));
        Charge charge = stripeServiceImpl.charge(chargeRequest);
        Assertions.assertThat(charge).isNotNull();
    }

    @Test
    void chargeTrue() throws StripeException {
        Trip trip=new Trip();
        trip.setId(1);

        ChargeRequest chargeRequest=new ChargeRequest();
        chargeRequest.setAmount(1000);
        chargeRequest.setDescription("desc");
        chargeRequest.setStripeToken("tok_visa");
        chargeRequest.setCurrency("CAD");
        chargeRequest.setTrip(1);

        Mockito.when( tripRepository.findById(1)).thenReturn(Optional.of(trip));
        Charge charge = stripeServiceImpl.charge(chargeRequest);
        assertEquals(charge.getAmount(),1000);
    }

    @Test
    void chargeFalse() throws StripeException {
        Trip trip=new Trip();
        trip.setId(1);

        ChargeRequest chargeRequest=new ChargeRequest();
        chargeRequest.setAmount(1000);
        chargeRequest.setDescription("desc");
        chargeRequest.setStripeToken("tok_visa");
        chargeRequest.setCurrency("CAD");
        chargeRequest.setTrip(1);

        Mockito.when( tripRepository.findById(1)).thenReturn(Optional.of(trip));
        Charge charge = stripeServiceImpl.charge(chargeRequest);
        assertFalse(charge.getAmount()!=1000);
    }
}