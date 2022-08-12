package com.solvd.university.people.staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dean extends Professor {

    private static final Logger LOGGER = LogManager.getLogger(Dean.class);

    public Dean(String firstName, String surname, String gender) {
        super(firstName, surname, gender);
    }

    @Override
    public void greet() {
        LOGGER.info("Hello students, my name is " + getTitle(getGender()) + " " + getFullName() + " and I am a dean. Nice to meet you!");
    }
}
