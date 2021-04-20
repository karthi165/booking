package com.maersk.booking.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.maersk.booking.repository.BookingRepository;

/*
 * A Simple Class to re fetch / update the id. So that subsequent calls during entity save operation can be avoided.
 * This is not a best design practice , however it works ok for a single instance app.
 */
@Component
public class OnApplicationStartUp {

	@Autowired
	private BookingRepository repository;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		String maxIdVal = repository.getLatestBookingReferenceId();
        if(maxIdVal==null) {
        	SimpleIdGenerator.setNextId(-1L);
        }
        else {
        	SimpleIdGenerator.setNextId(Long.parseLong(maxIdVal)+1);
        }
		
	}

}