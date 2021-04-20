package com.maersk.booking.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maersk.booking.exception.BadRequestException;
import com.maersk.booking.exception.BookingException;
import com.maersk.booking.exception.BookingPersistException;

@RestControllerAdvice
public class BookingControllerExceptionHandler {
	private final Log logger = LogFactory.getLog(getClass());

	@ExceptionHandler(BookingPersistException.class)
	public ResponseEntity<String> handleBookingPersistException(BookingPersistException e) {
		logger.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error saving response to database : " + e.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
		logger.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request Exception : " + e.getMessage());
	}

	@ExceptionHandler(BookingException.class)
	public ResponseEntity<String> handleBookingException(BookingException e) {
		logger.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking Exception  : " + e.getMessage());
	}
}
