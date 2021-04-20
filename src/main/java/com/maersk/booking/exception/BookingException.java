package com.maersk.booking.exception;

public class BookingException extends RuntimeException {

	private static final long serialVersionUID = 5069624613518444806L;

	public BookingException(String message) {
		super(message);
	}

	public BookingException(String message, Throwable cause) {
		super(message, cause);
	}
}
