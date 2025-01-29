package com.example.chapter7;

import java.nio.file.Paths;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;

public class CassandraConn {

    private CqlSession cqlSession;

    public CassandraConn(String host, String port, String keyspace, String localDatacenter) {

        try {
            cqlSession = CqlSession.builder()
                .addContactPoint(new java.net.InetSocketAddress(host, Integer.parseInt(port)))
                .withKeyspace(keyspace)
                .withLocalDatacenter(localDatacenter)
                .build();

            System.out.printf("[OK] Welcome to Cassandra DB! Connected to Keyspace %s.\n", cqlSession.getKeyspace().get());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public CqlSession getCqlSession() {
        return cqlSession;
    }

    protected void finalize() {
        cqlSession.close();
        System.out.println("[shutdown_driver] Closing connection");
        System.out.println();
    }
    
}
