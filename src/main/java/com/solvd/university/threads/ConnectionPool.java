package com.solvd.university.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;
    private final BlockingQueue<Connection> connections;
    private Connection connection;
    private final AtomicInteger connCount;

    private ConnectionPool(int poolSize) {
        connections = new LinkedBlockingQueue<>(poolSize);
        connCount = new AtomicInteger();
        for (int i = 0; i < poolSize; i++) {
            connection = new Connection();
            connections.add(connection);
        }
    }

    public synchronized Connection getConnection() {
        try {
            connection = connections.poll(10, TimeUnit.MILLISECONDS);
            if (connection == null) {
                if (connCount.get() < connections.size()) {
                    connection = new Connection();
                    connCount.incrementAndGet();
                }
                connection = connections.take();
            }
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        connections.offer(connection);
    }

    public static ConnectionPool getInstance(int poolSize) {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool(poolSize);
        }
        return INSTANCE;
    }
}