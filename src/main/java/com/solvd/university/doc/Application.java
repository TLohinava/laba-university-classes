package com.solvd.university.doc;

import com.solvd.university.exception.DateException;
import com.solvd.university.people.Student;
import com.solvd.university.people.staff.Employee;
import com.solvd.university.people.staff.Rector;
import com.solvd.university.structure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.*;
import java.util.*;

public class Application {

    private Student student;
    private Rector rector;
    private Faculty faculty;
    private University university;
    private List<TestCertificate> testCertificates;
    private SchoolCert schoolCert;
    private LocalDateTime applicationDateTime;
    private ApplicationStatus status;

    private static final Logger LOGGER = LogManager.getLogger(Employee.class);

    public Application(Student student, LocalDateTime applicationDateTime) {
        this.student = student;
        this.applicationDateTime = applicationDateTime;
    }

    public enum ApplicationStatus {
        SUBMITTED, WAITING, PROCESSED, REVIEWED, LOST
    }

    public int getTotalScore(List<TestCertificate> testCertificates, SchoolCert schoolCert) {
        for (TestCertificate cert : testCertificates) {
            if (cert.getDateOfIssue().getYear() < LocalDate.now().getYear()) {
                throw new DateException("Year is not valid");
            }
        }
        return testCertificates.get(0).getCertScore() + testCertificates.get(1).getCertScore() + testCertificates.get(2).getCertScore() + schoolCert.getCertScore();
    }

    @Override
    public String toString() {
        return String.format("To %s, the rector of %s/n I, %s, passport № %s, currently living at %s, would like to submit " +
                        "my application for faculty of %s",
                rector.getFullName(), university.getUniName(), student.getFullName(), student.getPassport(), student.getAddress(), faculty.getFacultyName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Application application = (Application) obj;
        return application.getApplicationDateTime() == this.getApplicationDateTime() && application.getStudent().equals(this.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationDateTime, student);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Rector getRector() {
        return rector;
    }

    public void setRector(Rector rector) {
        this.rector = rector;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public SchoolCert getSchoolCert() {
        return schoolCert;
    }

    public void setSchoolCert(SchoolCert schoolCert) {
        this.schoolCert = schoolCert;
    }

    public LocalDateTime getApplicationDateTime() {
        return applicationDateTime;
    }

    public void setApplicationDateTime(LocalDateTime applicationDateTime) {
        this.applicationDateTime = applicationDateTime;
    }

    public List<TestCertificate> getTestCertificates() {
        return testCertificates;
    }

    public void setTestCertificates(List<TestCertificate> testCertificates) {
        this.testCertificates = testCertificates;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}