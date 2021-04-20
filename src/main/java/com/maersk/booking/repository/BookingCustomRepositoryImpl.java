package com.maersk.booking.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

public class BookingCustomRepositoryImpl implements BookingCustomRepository {

	@Autowired
	private CassandraOperations cassandraTemplate;

	@Override
	public String getLatestBookingReferenceId() {
		String maxIdVal;
		try {
			maxIdVal = cassandraTemplate.selectOne("SELECT MAX(bookingreference) FROM booking ;", String.class);
		} catch (Exception e) {
			maxIdVal = null;
		}

		return maxIdVal;
	}

}
