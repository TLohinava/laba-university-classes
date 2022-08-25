package com.solvd.university.people.staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Rector extends Professor {

    private LocalDate start;
    private final int present;

    private static final Logger LOGGER = LogManager.getLogger(Rector.class);

    public Rector(String firstName, String surname, Gender gender) {
        super(firstName, surname, gender);
        this.present = LocalDate.now().getYear();
    }

    @Override
    public void greet() {
        LOGGER.info("Hello students, I am " + getTitle() + " " + getFullName() + " and I have been a rector for " + getYearsInOffice() + " years. Nice to meet you!");
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getPresent() {
        return present;
    }

    public int getYearsInOffice() {
        return getPresent() - getStart().getYear();
    }
}
