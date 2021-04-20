package com.maersk.booking.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.maersk.booking.model.Booking;

import reactor.core.publisher.Mono;

@Repository
public interface BookingRepository extends ReactiveCassandraRepository<Booking, String>,BookingCustomRepository {
   
	@AllowFiltering()
	@Query("SELECT MAX(bookingreference) FROM booking ;")
	public Mono<String> getMaxId();
	
	public Mono<Booking> findTop1ByOrderByBookingReferenceDesc();
	
}