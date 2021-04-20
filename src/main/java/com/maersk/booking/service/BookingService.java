package com.maersk.booking.service;

import java.util.Map;

import com.maersk.booking.model.Booking;

import reactor.core.publisher.Mono;

public interface BookingService {

	public Mono<Booking> addBooking(Booking bookingObject);
	public Map<String, Boolean>    checkBooking(Booking bookingObject);
}
