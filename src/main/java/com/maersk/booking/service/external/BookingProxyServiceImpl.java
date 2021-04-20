package com.maersk.booking.service.external;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.datastax.oss.driver.shaded.guava.common.net.HttpHeaders;
import com.maersk.booking.exception.BadRequestException;
import com.maersk.booking.exception.BookingException;
import com.maersk.booking.model.Booking;

import reactor.core.publisher.Mono;

@Service("bookingProxyService")
public class BookingProxyServiceImpl implements BookingProxyService {

	private static final Logger logger = LogManager.getLogger();

	private WebClient webClient;

	public BookingProxyServiceImpl() {

		this(getBookingProxyHost());
	}

	public BookingProxyServiceImpl(String bookingProxyHost) {
		this.webClient = WebClient.create(bookingProxyHost);

	}

	@Value("${maersk.external.booking.serivce.url:https://maersk.com}")
	private static String hostOverride;

	@Value("${maersk.external.booking.serivce.endpoint:/api/bookings/checkAvailable}")
	private static String endPoint;

	public static String getBookingProxyHost() {
		return hostOverride;
	}

	public static String getEndPoint() {
		return Optional.ofNullable(endPoint).orElse("/api/bookings/checkAvailable");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Mono<Map<String, Integer>> checkBooking(Booking bookingDetails) {

		logger.info("Calling POST method on external service :");
		Mono<?> responseMap = webClient.post().uri(getEndPoint())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(bookingDetails), Booking.class).retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
					throw new BadRequestException("Bad Request");
				}).onStatus(HttpStatus::is5xxServerError, clientResponse -> {
					throw new BookingException("Bad Request");
				}).bodyToMono(Map.class);
		return (Mono<Map<String, Integer>>) responseMap;
	}
}
