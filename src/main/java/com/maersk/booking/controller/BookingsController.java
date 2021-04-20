package com.maersk.booking.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.maersk.booking.common.BookingResponseConstants;
import com.maersk.booking.model.Booking;
import com.maersk.booking.service.BookingService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/bookings")
public class BookingsController {

	private BookingService bookingService;

	public BookingsController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> addBooking(@Valid @RequestBody Booking bookingInfo,
			UriComponentsBuilder uriComponentsBuilder) {
		Map<String, String> responseMap = new HashMap<>();
		bookingService.addBooking(bookingInfo)
				.map(createdBooking -> responseMap.put("bookingReference", createdBooking.getBookingReference()))
				.log("Booking reserved successfully.", Level.INFO);

		UriComponents uriComponents = uriComponentsBuilder.path("/api/bookings/{id}")
				.buildAndExpand(responseMap.get(BookingResponseConstants.bookingReference));
		return ResponseEntity.created(uriComponents.toUri()).body(responseMap);
	}

	@PostMapping(value = "/checkBooking", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<Map<String, Boolean>>> checkBooking(@Valid @RequestBody Booking bookingInfo) {
		return ResponseEntity.status(HttpStatus.OK).body(Mono.just(bookingService.checkBooking(bookingInfo)));

	}

}
