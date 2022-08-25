package com.solvd.university.people;

import com.solvd.university.doc.Passport;
import com.solvd.university.structure.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Student extends Person {

    private boolean passedTest;
    private Passport passport;
    private Address address;

    private static final Logger LOGGER = LogManager.getLogger(Student.class);

    public Student(String firstName, String surname, Gender gender) {
        super(firstName, surname, gender);
    }

    @Override
    public void greet() {
        LOGGER.info("Hello, I am " + getFullName() + " and I am a student.");
    }

    public boolean getPassedTest() {
        return passedTest;
    }

    public void setPassedTest(boolean passedTest) {
        this.passedTest = passedTest;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
