package com.solvd.university.people.staff;

import com.solvd.university.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee extends Person implements IDrink {

    private static final Logger LOGGER = LogManager.getLogger(Employee.class);

    IDrink.Drinks drinkPreference;

    public Employee(String firstName, String surname, Gender gender) {
        super(firstName, surname, gender);
    }

    @Override
    public void greet() {
        LOGGER.info("Hello colleagues!");
    }

    @Override
    public void think(IDrink.Drinks drink) {
        LOGGER.info("It's time for a hot " + drink.getDrinkType() + "!");
    }

    @Override
    public void boilWater() {
        LOGGER.info("Water is ready!");
    }

    @Override
    public void brew(IDrink.Drinks drink) {
        switch (drink) {
            case TEA:
                LOGGER.info("Wait 5 minutes for the " + drink.getDrinkType() + " to get ready.");
                break;
            case COFFEE:
                LOGGER.info("Wait for the " + drink.getDrinkType() + " to pour.");
                break;
            case COCOA:
                LOGGER.info(drink.getDrinkType() + " is ready.");
                break;
            default:
                LOGGER.info("The drink is ready.");
                break;
        }
    }

    @Override
    public void sip() {
        LOGGER.info("Damn good stuff");
    }

    public Drinks getDrinkPreference() {
        return drinkPreference;
    }

    public void setDrinkPreference(Drinks drinkPreference) {
        this.drinkPreference = drinkPreference;
    }
}