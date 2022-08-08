package doc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.*;
import java.util.Objects;

public class TestCertificate extends Certificate {

    private LocalDate dateOfIssue;
    private String subject;

    private static final Logger LOGGER = LogManager.getLogger(TestCertificate.class);

    public TestCertificate(LocalDate dateOfIssue, int certScore, String subject) {
        super(certScore);
        this.dateOfIssue = dateOfIssue;
        this.subject = subject;
    }

    public static int calcCertScore() {
        return Math.toIntExact(Math.round(Math.random() * 100 + 1));
    }

    @Override
    public void stamp() {
        LOGGER.info("Certified");
    }

    @Override
    public String toString() {
        return this.subject + " " + this.getCertScore();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof TestCertificate)) {
            return false;
        }
        TestCertificate testC = (TestCertificate) obj;
        return testC.dateOfIssue == this.dateOfIssue && testC.subject.equals(this.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfIssue, subject);
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject() {
        this.subject = subject;
    }
}