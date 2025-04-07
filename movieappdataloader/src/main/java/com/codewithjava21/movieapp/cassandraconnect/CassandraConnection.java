package com.codewithjava21.movieapp.cassandraconnect;

import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.util.List;
import com.datastax.oss.driver.api.core.CqlSession;

public class CassandraConnection {

    private CqlSession cqlSession;

    public CassandraConnection(String username, String pwd,
            List<InetSocketAddress> endpointList, String keyspace,
            String datacenter) {

        try {
            cqlSession = CqlSession.builder()
                .addContactPoints(endpointList)
                .withAuthCredentials(username, pwd)
                .withKeyspace(keyspace)
                .withLocalDatacenter(datacenter)
                .build();

            System.out.println("[OK] Success");
            System.out.printf("[OK] Welcome to Apache Cassandra! Connected to Keyspace %s\n", cqlSession.getKeyspace().get());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // public CassandraConnection(String username, String pwd,
    //         String secureBundleLocation, String keyspace) {

    //     try {
    //         cqlSession = CqlSession.builder()
    //             .withCloudSecureConnectBundle(Paths.get(secureBundleLocation))
    //             .withAuthCredentials(username, keyspace)
    //             .withKeyspace(keyspace)
    //             .build();
            
    //         System.out.println("[OK] Success");
    //         System.out.printf("[OK] Welcome to Apache Cassandra! Connected to Keyspace %s\n", cqlSession.getKeyspace().get());
    //     } catch (Exception ex) {
    //         System.out.println(ex.getMessage());
    //     }
    // }

    public CassandraConnection(String host, String port, 
            String keyspace, String localDatacenter) {

        try {
            cqlSession = CqlSession.builder()
                .addContactPoint(new java.net.InetSocketAddress(host, Integer.parseInt(port)))
                .withKeyspace(keyspace)
                .withLocalDatacenter(localDatacenter)
                .build();

                System.out.println("[OK] Success");
                System.out.printf("[OK] Welcome to Apache Cassandra! Connected to Keyspace %s\n", cqlSession.getKeyspace().get());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public CqlSession getCqlSession() {
        return cqlSession;
    }

    protected void finalize() {
        System.out.println("[shutdown_driver] Closing connection");
        System.out.println();
        cqlSession.close();
    }
    
}
