package doc;

import people.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logbook implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(Logbook.class);

    private Student student;
    int idNumber;

    public Logbook(Student student, int idNumber) {
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

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
}
