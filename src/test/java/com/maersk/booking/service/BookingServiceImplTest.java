package com.maersk.booking.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.maersk.booking.common.BookingResponseConstants;
import com.maersk.booking.common.ContainerType;
import com.maersk.booking.common.SimpleIdGenerator;
import com.maersk.booking.common.TestGroup;
import com.maersk.booking.model.Booking;
import com.maersk.booking.repository.BookingRepository;
import com.maersk.booking.service.external.BookingProxyService;

import reactor.core.publisher.Mono;

public class BookingServiceImplTest {

	@Mock
	private BookingRepository bookingRepo;
	
	@Mock
	private BookingProxyService bookingProxyService;
	
	@InjectMocks
	private BookingServiceImpl bookingService;
	
	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	@DataProvider(name="getInputResponseData")
	public static Object[][] getInputResponseData() {
		return new Object[][]{
			{Map.of(BookingResponseConstants.availableSpace,5),Map.of(BookingResponseConstants.available,true)}, 
			{Map.of(BookingResponseConstants.availableSpace,0),Map.of(BookingResponseConstants.available,false)}
			
		};
	}
	
	@Test(groups = { TestGroup.UNIT },dataProvider = "getInputResponseData")
	private void checkBookingTest(Map<String,Integer> proxyServiceOutput ,Map<String,Boolean> expectedOutput) {
		Booking bookingDetailsInp = Booking.getBuilder().containerSize(20).containerType(ContainerType.REEFER)
				.destintation("NYCAA").orgin("HKGAA").quantity(75).build();
		

		when(bookingProxyService.checkBooking(bookingDetailsInp)).thenReturn(Mono.just(proxyServiceOutput));

		Map<String,Boolean> responseMap=bookingService.checkBooking(bookingDetailsInp);
		
		assertEquals(responseMap,expectedOutput);
		
		Mockito.verify(bookingProxyService, times(1)).checkBooking(bookingDetailsInp);
	}
	
	
	
	@Test(groups = { TestGroup.UNIT })
	private void addBookingTest() {
		SimpleIdGenerator.setNextId(20190L);
		Booking bookingDetailsInp = Booking.getBuilder().containerSize(20).containerType(ContainerType.REEFER)
				.destintation("NYCAA").orgin("HKGAA").quantity(75).build();
		Booking bookingDetails = Booking.getBuilder().containerSize(20).containerType(ContainerType.REEFER)
				.destintation("NYCAA").orgin("HKGAA").quantity(75).build();
		bookingDetails.setBookingReference(Long.toString(20190L));

		when(bookingRepo.save(bookingDetails)).thenReturn(Mono.just(bookingDetails));

		bookingService.addBooking(bookingDetailsInp);
		Mockito.verify(bookingRepo, times(1)).save(bookingDetails);
	}
}
