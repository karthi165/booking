package com.maersk.booking.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.maersk.booking.common.BookingResponseConstants;
import com.maersk.booking.common.SimpleIdGenerator;
import com.maersk.booking.exception.BookingPersistException;
import com.maersk.booking.model.Booking;
import com.maersk.booking.repository.BookingRepository;
import com.maersk.booking.service.external.BookingProxyService;

import reactor.core.publisher.Mono;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	private BookingRepository bookingRepo;
	private BookingProxyService bookingProxyService;

	protected static final Logger logger = LogManager.getLogger();

	public BookingServiceImpl(BookingRepository bookingRepo, BookingProxyService bookingProxyService) {
		this.bookingRepo = bookingRepo;
		this.bookingProxyService = bookingProxyService;
	}

	@Override
	public Mono<Booking> addBooking(Booking bookingObject) {
		bookingObject.setBookingReference(String.valueOf(SimpleIdGenerator.getNextId()));
		return bookingRepo.save(bookingObject).doOnError(errorA -> {
			throw new BookingPersistException(errorA.getLocalizedMessage());
		});

	}

	@Override
	public Map<String, Boolean> checkBooking(Booking bookingObject) {
		Map<String, Integer> availableSpace = bookingProxyService.checkBooking(bookingObject).block();
		if (availableSpace.get(BookingResponseConstants.availableSpace) > 0) {
			return Map.of(BookingResponseConstants.available, true);
		} else {
			return Map.of(BookingResponseConstants.available, false);

		}
	}

}
