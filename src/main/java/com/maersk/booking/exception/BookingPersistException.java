package com.maersk.booking.exception;

public class BookingPersistException extends RuntimeException {

	private static final long serialVersionUID = 5069624613518444806L;

	public BookingPersistException(String message) {
		super(message);
	}

	public BookingPersistException(String message, Throwable cause) {
		super(message, cause);
	}
}
