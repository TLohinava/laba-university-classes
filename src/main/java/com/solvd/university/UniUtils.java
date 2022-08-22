package com.solvd.university;

import com.solvd.university.doc.Application;
import com.solvd.university.people.staff.*;
import com.solvd.university.structure.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

public class UniUtils {

    private static final Logger LOGGER = LogManager.getLogger(UniUtils.class);

    public static void getCheapest(University university) {
        BigDecimal min = new BigDecimal(10000);
        String fcName = "";

        for (Faculty fc : university.getFaculty()) {
            if (min.compareTo(fc.getCost()) >= 0) {
                min = fc.getCost();
                if (fcName.isEmpty()) {
                    fcName += fc.getFacultyName();
                } else {
                    fcName += " and " + fc.getFacultyName();
                }
            }
        }
        LOGGER.info("The lowest cost of studying is at " + fcName + " with " + min + "$ per year.");
    }

    public static void chooseFaculty(Faculty faculty, Application application, int total) {
        if (application.getStudent().getPassedTest() && total >= faculty.getMinScore()) {
            faculty.introduceFaculty();
            faculty.admitStudents(application.getStudent());
        } else {
            LOGGER.info("Unfortunately, your score of " + total + " is not sufficient to apply for faculty of " +
                    faculty.getFacultyName() + " to study for free. " +
                    "The cost of studying here would be " + faculty.getCost() + "$ per year.");
        }
    }

    public static void welcome(List<Employee> employees) {
        employees.forEach(Employee::greet);
    }

    public static void drinkBreak(Employee employee) {
        employee.think(employee.getDrinkPreference());
        employee.boilWater();
        employee.brew(employee.getDrinkPreference());
        employee.sip();
    }

    public static void checkSchedule(Employee employee, LocalTime time) {
        LocalTime midday = LocalTime.of(12, 0);
        if (time.isBefore(midday)) {
            employee.greet();
        } else if (time.equals(midday)) {
            drinkBreak(employee);
        } else {
            if (employee instanceof Professor) {
                ((Professor) employee).writeReview();
            } else {
                employee.sign();
            }
        }
    }

    public static void checkStatus(Application application) {
        switch (application.getStatus()) {
            case SUBMITTED:
                LOGGER.info("Your application has been submitted, thank you.");
                break;
            case WAITING:
                LOGGER.info("Your application is still in the waiting line.");
                break;
            case PROCESSED:
                LOGGER.info("Your application is being processed.");
                break;
            case REVIEWED:
                LOGGER.info("We have reviewed your application.");
                break;
            case LOST:
            default:
                LOGGER.info("We've lost your application. Sorry!");
                break;
        }
    }
}