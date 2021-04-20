package com.maersk.booking.service.external;

import java.util.Map;

import com.maersk.booking.model.Booking;

import reactor.core.publisher.Mono;

public interface BookingProxyService {

	Mono<Map<String, Integer>> checkBooking(Booking bookingDetails);

}
