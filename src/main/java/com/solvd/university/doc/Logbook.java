package com.solvd.university.doc;

import com.solvd.university.people.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Logbook<T> implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(Logbook.class);

    private Student student;
    private T idNumber;
    private List<PassbookEntry<T>> passbook;

    public Logbook(Student student, T idNumber) {
        this.student = student;
        this.idNumber = idNumber;
        this.passbook = new ArrayList<>();
    }

    @Override
    public void close() {
        LOGGER.info("Logbook closed");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public T getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(T idNumber) {
        this.idNumber = idNumber;
    }

    public List<PassbookEntry<T>> getPassbook() {
        return passbook;
    }

    public void setPassbook(List<PassbookEntry<T>> passbook) {
        this.passbook = passbook;
    }
}
