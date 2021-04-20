package com.maersk.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories
public class CustomerPortalApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPortalApplication.class, args);
	}

}
