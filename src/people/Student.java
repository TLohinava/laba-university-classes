package people;

import doc.Passport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structure.Address;

public class Student extends Person {

    public boolean passedTest;
    private Passport passport;
    private Address address;

    private static final Logger LOGGER = LogManager.getLogger(Student.class);

    public Student(String firstName, String surname, String gender) {
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
