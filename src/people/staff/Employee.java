package people.staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.*;

public class Employee extends Person implements IDrink {

    private static final Logger LOGGER = LogManager.getLogger(Employee.class);

    public Employee(String firstName, String surname, String gender) {
        super(firstName, surname, gender);
    }

    @Override
    public void greet() {
        LOGGER.info("Hello colleagues!");
    }

    @Override
    public void think() {
        LOGGER.info("It's time for a hot drink!");
    }

    @Override
    public void boilWater() {
        LOGGER.info("Water is ready!");
    }

    @Override
    public void brew() {
        LOGGER.info("Wait 5 minutes for the drink to get ready.");
    }

    @Override
    public void sip() {
        LOGGER.info("Damn good stuff");
    }
}