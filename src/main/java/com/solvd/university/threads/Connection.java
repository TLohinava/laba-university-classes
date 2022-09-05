package com.solvd.university.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    @Override
    public void run() {
        create();
        read();
        update();
        delete();
        repeat();
    }

    public void create() {
        LOGGER.info(1);
    }

    public void read() {
        LOGGER.info(2);
    }

    public void update() {
        LOGGER.info(3);
    }

    public void delete() {
        LOGGER.info(4);
    }

    public void repeat() {
        LOGGER.info(5);
    }
}