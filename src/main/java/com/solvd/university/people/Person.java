package com.solvd.university.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Person implements IWrite {

    private String firstName;
    private String surname;
    private String gender;

    private static final Logger LOGGER = LogManager.getLogger(Person.class);

    public Person(String firstName, String surname, String gender) {
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
    }

    public abstract void greet();

    @Override
    public void sign() {
        LOGGER.info("Hereby I sign this document");
    }

    @Override
    public String toString() {
        return this.getFullName();
    }

    public String getFullName() {
        return getFirstName() + " " + getSurname();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
