package com.group18.listener;

import com.group18.entity.Trip;
import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class OnTripAcceptEvent extends ApplicationEvent {
    private final Trip trip;

    public OnTripAcceptEvent(final Trip trip) {
        super(trip);
        this.trip = trip;
    }

    public Trip getTrip() {
        return trip;
    }
}
