package com.solvd.university.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;
    private final BlockingQueue<Connection> connections;

    private ConnectionPool(int poolSize) {
        connections = new LinkedBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = new Connection();
            connections.add(connection);
        }
    }

    public synchronized Connection getConnection() {
        try {
            return connections.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    public static ConnectionPool getInstance(int poolSize) {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool(poolSize);
        }
        return INSTANCE;
    }
}