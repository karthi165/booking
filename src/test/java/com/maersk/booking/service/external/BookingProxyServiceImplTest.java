package com.maersk.booking.service.external;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maersk.booking.common.ContainerType;
import com.maersk.booking.common.TestGroup;
import com.maersk.booking.model.Booking;

import io.swagger.v3.core.util.Json;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Mono;

public class BookingProxyServiceImplTest {

	private BookingProxyServiceImpl boookingProxyService;

	public static MockWebServer mockBackEnd;

	@BeforeClass(alwaysRun = true)
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
	}

	@AfterClass(alwaysRun = true)
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@BeforeMethod(alwaysRun = true)
	void initialize() {
		String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
		boookingProxyService = new BookingProxyServiceImpl(baseUrl);
	}

	@Test(groups = { TestGroup.UNIT })
	public void test_checkBooking() throws JsonProcessingException {
		Booking bookingDetailsInp = Booking.getBuilder().containerSize(20).containerType(ContainerType.REEFER)
				.destintation("NYCAA").orgin("HKGAA").quantity(75).build();

		Map<String, Integer> inputMap = Map.of("availableSpace", 5);
		mockBackEnd.enqueue(new MockResponse().addHeader("Content-Type", "application/json")
				.setBody(Json.mapper().writeValueAsString(inputMap)));
		Mono<Map<String, Integer>> monoResponse = boookingProxyService.checkBooking(bookingDetailsInp);
		Map<String, Integer> mapResponse = monoResponse.block();
		assertEquals(mapResponse, inputMap);
	}
	
}
