package com.solvd.university.doc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.university.people.Student;

public abstract class Certificate implements IShred {

    private Student student;
    private int certScore;

    private static final Logger LOGGER = LogManager.getLogger(Certificate.class);

    public Certificate(int certScore) {
        this.certScore = certScore;
    }

    public abstract void stamp();

    @Override
    public void shred() {
        LOGGER.info("bzzzzzz. The document is gone");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getCertScore() {
        return certScore;
    }

    public void setCertScore(int certScore) {
        this.certScore = certScore;
    }
}