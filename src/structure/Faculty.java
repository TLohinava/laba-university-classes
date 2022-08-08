package structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import people.staff.*;
import people.Student;
import exception.DataInvalidException;

import java.math.BigDecimal;

public class Faculty implements IAdmit {

    private String facultyName;
    private Student[] students;
    private int year;
    private Dean dean;
    private Employee[] employees;
    private int minScore;
    private BigDecimal cost;

    private static final Logger LOGGER = LogManager.getLogger(Faculty.class);

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public void introduceFaculty() {
        LOGGER.info("Welcome to the faculty of " + getFacultyName() + ". Our year of establishment is " +
                getYear() + ". Our is dean is called " + dean.getTitle(dean.getGender()) + dean.getFullName() + ". There are " +
                getStudents().length + " students studying at our faculty.");
    }

    @Override
    public void admitStudents(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return this.facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
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
}

