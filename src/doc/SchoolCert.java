package doc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SchoolCert extends Certificate {

    private static final Logger LOGGER = LogManager.getLogger(SchoolCert.class);

    public SchoolCert(int certScore) {
        super(certScore);
    }

    @Override
    public void stamp() {
        LOGGER.info("Graduated");
    }

    @Override
    public void shred() {
        LOGGER.info("bzzzzzz. The document is gone");
    }

    @Override
    public String toString() {
        return "School certificate score is " + this.getCertScore();
    }
}