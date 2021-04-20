package com.maersk.booking.common;

/*
 * Simple Id Generator , assuming a single instance of app server is running .
 * If multiple instances are running another centralized id generator 
 * service is preferred or ID should be UUID based instead of sequentialy 
 * incrementing numbers.
 */
public class SimpleIdGenerator {

	public static final Long baseId = (long) 957000001;
	public static Long nextId;

	public static Long getNextId() {
		return nextId++;
	}

	public static void setNextId(Long nextId) {
		if (nextId == -1L) {
			SimpleIdGenerator.nextId = baseId;
		} else {
			SimpleIdGenerator.nextId = nextId;
		}
	}

}
