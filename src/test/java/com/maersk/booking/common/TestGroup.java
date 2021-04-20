package com.maersk.booking.common;

public interface TestGroup {
	String UNIT = "mocked"; // Fast tests, no Spring context load or database access
	String FUNCTIONAL = "functional";
}
