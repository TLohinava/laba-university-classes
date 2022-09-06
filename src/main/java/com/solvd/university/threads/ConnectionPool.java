package com.solvd.university.threads;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;
    private final List<Connection> connections;
    private Connection connection;
    private static int POOL_SIZE;

    private ConnectionPool() {
        POOL_SIZE = 5;
        connections = new ArrayList<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            connection = new Connection();
            connections.add(connection);
        }
    }

    public synchronized Connection getConnection() {
        connection = connections.remove(connections.size() - 1);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }
}