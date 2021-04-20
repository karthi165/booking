package com.maersk.booking.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maersk.booking.common.ContainerType;
import com.maersk.booking.common.TestGroup;
import com.maersk.booking.model.Booking;
import com.maersk.booking.service.BookingService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookingsController.class)
@AutoConfigureWebTestClient
public class BookingControllerTest {

	@Mock
	private BookingService bookingService;

	@InjectMocks
	private BookingsController bookingController;

	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@Test(groups = { TestGroup.UNIT })
	private void addBookingTest() {
		Booking bookingDetails = Booking.getBuilder().containerSize(20).containerType(ContainerType.REEFER)
				.destintation("NYCAA").orgin("HKGAA").quantity(75).build();

		Booking bookingDetailsResp = Booking.getBuilder().containerSize(20).containerType(ContainerType.REEFER)
				.destintation("NYCAA").orgin("HKGAA").quantity(75).build();
		bookingDetailsResp.setBookingReference("95788062");
		when(bookingService.addBooking(bookingDetails)).thenReturn(Mono.just(bookingDetailsResp));

		WebTestClient.bindToController(bookingController).build().post().uri("/api/bookings")
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(bookingDetails)).exchange()
				.expectStatus().isCreated();

		Mockito.verify(bookingService, times(1)).addBooking(bookingDetails);

	}

	@SuppressWarnings("unchecked")
	@Test(groups = { TestGroup.UNIT })
	private void checkBookingTest() {
		Booking bookingDetails = Booking.getBuilder().containerSize(20).containerType(ContainerType.REEFER)
				.destintation("NYCAA").orgin("HKGAA").quantity(75).build();

		when(bookingService.checkBooking(bookingDetails)).thenReturn(Map.of("available", true));

		WebTestClient.bindToController(bookingController).build().post().uri("/api/bookings/checkBooking")
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(bookingDetails)).exchange()
				.expectStatus().isOk();

		Mockito.verify(bookingService, times(1)).checkBooking(bookingDetails);

	}
	
	
}
