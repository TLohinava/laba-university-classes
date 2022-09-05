package com.solvd.university.threads;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;
    private final List<Connection> connections;
    private final List<Connection> usedConnections;
    private Connection connection;
    private static int POOL_SIZE;

    public ConnectionPool() {
        POOL_SIZE = 5;
        usedConnections = new ArrayList<>();
        connections = new ArrayList<>(POOL_SIZE);
    }

    public synchronized Connection getConnection() {
        connection = null;
        if (connections.size() > 0) {
            connection = connections.remove(connections.size() - 1);
            usedConnections.add(connection);
        } else {
            for (int i = 0; i < POOL_SIZE; i++) {
                connection = new Connection();
                usedConnections.add(connection);
            }
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connections.size() < POOL_SIZE) {
            connection = usedConnections.get(usedConnections.indexOf(connection));
            connections.add(connection);
            usedConnections.remove(connection);
        }
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }
}