package com.maersk.booking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(classes = CustomerPortalApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
class CustomerPortalApplicationTests {

	@Test
	void contextLoads() {
	}

}
