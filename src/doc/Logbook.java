package doc;

import people.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logbook<T> implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(Logbook.class);

    private Student student;
    T idNumber;

    public Logbook(Student student, T idNumber) {
        this.student = student;
        this.idNumber = idNumber;
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
}
