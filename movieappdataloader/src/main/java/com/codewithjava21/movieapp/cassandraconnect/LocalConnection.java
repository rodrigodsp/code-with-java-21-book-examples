package com.codewithjava21.movieapp.cassandraconnect;

import com.datastax.oss.driver.api.core.CqlSession;

public class LocalConnection extends CassandraConnection {

    static final String CASSANDRA_DB_HOST = System.getenv("CASSANDRA_DB_HOST");
    static final String CASSANDRA_DB_PORT = System.getenv("CASSANDRA_DB_PORT");
    static final String CASSANDRA_DB_KEYSPACE = System.getenv("CASSANDRA_DB_KEYSPACE");
    static final String CASSANDRA_DB_LOCAL_DATACENTER = System.getenv("CASSANDRA_DB_LOCAL_DATACENTER");

    public LocalConnection() {
        super(CASSANDRA_DB_HOST, CASSANDRA_DB_PORT, 
                CASSANDRA_DB_KEYSPACE, CASSANDRA_DB_LOCAL_DATACENTER);        
    }
    
}
