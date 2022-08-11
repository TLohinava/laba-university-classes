package structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import people.staff.*;
import people.Student;
import exception.DataInvalidException;

import java.math.BigDecimal;
import java.util.*;

public class Faculty implements IAdmit {

    private String facultyName;
    private List<Student> students;
    private int maxNumberOfStudents;
    private int year;
    private Dean dean;
    private List<Employee> employees;
    private int minScore;
    private BigDecimal cost;
    private int studentsCapacity;

    private static final Logger LOGGER = LogManager.getLogger(Faculty.class);

    public Faculty(String facultyName, int studentsCapacity) {
        this.facultyName = facultyName;
        this.studentsCapacity = studentsCapacity;
        this.students = new ArrayList<>(studentsCapacity);
    }

    public void introduceFaculty() {
        LOGGER.info("Welcome to the faculty of " + getFacultyName() + ". Our year of establishment is " +
                getYear() + ". Our is dean is called " + dean.getTitle(dean.getGender()) + dean.getFullName() + ". There are " +
                getStudentsCapacity() + " students studying at our faculty.");
    }

    @Override
    public void admitStudents(Student student) {
        if (students.size() < getStudentsCapacity()) {
            students.add(student);
        } else {
            LOGGER.info("Unfortunately, we've reached the limit of students this year.");
        }
        LOGGER.info(students);
    }

    @Override
    public String toString() {
        return this.facultyName;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Dean getDean() {
        return dean;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) throws DataInvalidException {
        if (minScore <= 0) {
            throw new DataInvalidException("Minimum score is invalid");
        }
        this.minScore = minScore;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getStudentsCapacity() {
        return studentsCapacity;
    }

    public void setStudentsCapacity(int studentsCapacity) {
        this.studentsCapacity = studentsCapacity;
    }
}

