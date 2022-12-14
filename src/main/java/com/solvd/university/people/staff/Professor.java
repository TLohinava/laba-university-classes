package com.solvd.university.people.staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Professor extends Employee implements IResearch {

    private static final Logger LOGGER = LogManager.getLogger(Professor.class);

    public Professor(String firstName, String surname, Gender gender) {
        super(firstName, surname, gender);
    }

    @Override
    public void greet() {
        LOGGER.info("Hello students!");
    }

    @Override
    public void writeReview() {
        LOGGER.info("I have to write a review for this paper.");
    }

    public String getTitle() {
        if ((this.getGender()).equals(Gender.MALE)) {
            return "Mr.";
        } else if ((this.getGender()).equals(Gender.FEMALE)) {
            return "Ms.";
        } else {
            return "Mx.";
        }
    }

    @Override
    public String toString() {
        return this.getFullName();
    }
}
