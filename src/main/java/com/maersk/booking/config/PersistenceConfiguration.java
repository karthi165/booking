package com.maersk.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;

@Configuration
public class PersistenceConfiguration extends AbstractCassandraConfiguration {

	private static final String CASSANDRA_PORT="spring.data.cassandra.port";
	private static final String CASSANDRA_HOST="spring.data.cassandra.contact-points";
	private static final String CASSANDRA_KEYSPACE="spring.data.cassandra.keyspace-name";
	
	
	@Autowired
	private Environment env;
	

	@Override
	protected String getKeyspaceName() {
		return env.getProperty(CASSANDRA_KEYSPACE);
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}
}
