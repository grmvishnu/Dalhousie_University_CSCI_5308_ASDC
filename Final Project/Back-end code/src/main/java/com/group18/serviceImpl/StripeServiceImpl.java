package com.group18.serviceImpl;

import com.group18.entity.Trip;
import com.group18.entity.dto.ChargeRequest;
import com.group18.repository.TripRepository;
import com.group18.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StripeServiceImpl implements StripeService {
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    private TripRepository tripRepository;

    StripeServiceImpl(TripRepository tripRepository) {
        this.tripRepository=tripRepository;
    }

    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        Charge charge = Charge.create(chargeParams);
        updateTrip(chargeRequest);
        return charge;
    }

    private void updateTrip(ChargeRequest chargeRequest) {
        Optional<Trip> trip = tripRepository.findById(chargeRequest.getTrip());
        if(trip.isPresent()) {
            Trip tripObject = trip.get();
            tripObject.setPrepayment(chargeRequest.getAmount());
            tripRepository.save(tripObject);
        }
    }

}
