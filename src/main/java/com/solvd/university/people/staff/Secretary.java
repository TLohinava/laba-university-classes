package com.solvd.university.people.staff;

import com.solvd.university.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Secretary extends Employee {

    private static final Logger LOGGER = LogManager.getLogger(Secretary.class);

    public Secretary(String firstName, String surname, Gender gender) {
        super(firstName, surname, gender);
    }

    @Override
    public void greet() {
        LOGGER.info("Hello there.");
    }

    public void work(Person person) {
        if (person.equals(this)) {
            LOGGER.info("I work alone.");
        } else {
            LOGGER.info("I work with " + person.getFullName());
        }
    }
}
