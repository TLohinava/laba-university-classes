import doc.*;
import people.Student;
import people.staff.*;
import structure.*;
import exception.DataInvalidException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.*;

public class MainClass {

    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) {
        Rector rector = new Rector("Alexey", "Mikhailov", "M");
        rector.setStart(LocalDate.of(2011, 5, 2));

        Dean deanGM = new Dean("Ivan", "Ivanov", "M");
        Dean deanPed = new Dean("Zinaida", "Zinaidovna", "F");
        Dean deanDent = new Dean("Mikhail", "Mikhailov", "M");
        Dean deanPharm = new Dean("Zukhara", "Karimova", "F");

        Employee[] employeesGM = {
                deanGM,
                new Professor("Andrey", "Andreevich", "M"),
                new Professor("Anna", "Annova", "F")};

        Student alex = new Student("Alex", "May", "M");
        alex.setPassedTest(true);

        Secretary secretary = new Secretary("Alisa", "Alisovna", "F");
        secretary.work(employeesGM[1]);

        University bsmu = new University("Belarusian State Medical University", LocalDate.of(1921, 1, 1).getYear());
        int dateOfEst = bsmu.getDateOfEstablishment();

        Faculty generalMedicine = new Faculty("General Medicine");
        generalMedicine.setDean(deanGM);
        generalMedicine.setStudents(new Student[1200]);
        generalMedicine.setCost(new BigDecimal(3000));
        generalMedicine.setYear(dateOfEst);
        Faculty pediatrics = new Faculty("Pediatrics");
        pediatrics.setDean(deanPed);
        pediatrics.setStudents(new Student[200]);
        pediatrics.setCost(new BigDecimal(3000));
        pediatrics.setYear(dateOfEst);
        Faculty dentistry = new Faculty("Dentistry");
        dentistry.setDean(deanDent);
        dentistry.setStudents(new Student[100]);
        dentistry.setCost(new BigDecimal(4000));
        dentistry.setYear(1931);
        Faculty pharmacology = new Faculty("Pharmacology");
        pharmacology.setDean(deanPharm);
        pharmacology.setStudents(new Student[50]);
        pharmacology.setCost(new BigDecimal(5000));
        pharmacology.setYear(2011);

        try {
            generalMedicine.setMinScore(330);
            pediatrics.setMinScore(310);
            dentistry.setMinScore(360);
            pharmacology.setMinScore(390);
        } catch (DataInvalidException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            LOGGER.info("Minimum score setting is finished");
        }

        Faculty[] bsmuFaculties = {generalMedicine, pediatrics, dentistry, pharmacology};
        bsmu.setFaculty(bsmuFaculties);

        Address alexAddress = new Address("Belarus", "Minsk");
        alexAddress.setStreet("Mogilevskaya str.");
        alexAddress.setHouse(8);

        Passport alexPassport = new Passport("MP", 876543, alexAddress, LocalDate.of(2021, 3, 2));

        TestCertificate alexCertBio = new TestCertificate(LocalDate.of(2022, 6, 17),
                TestCertificate.calcCertScore(),
                "Biology");
        TestCertificate alexCertBel = new TestCertificate(LocalDate.of(2022, 6, 10),
                TestCertificate.calcCertScore(),
                "Belarusian");
        TestCertificate alexCertChem = new TestCertificate(LocalDate.of(2022, 6, 20),
                TestCertificate.calcCertScore(),
                "Chemistry");
        TestCertificate[] alexTestCerts = {alexCertBio, alexCertBel, alexCertChem};

        SchoolCert alexSchoolCert = new SchoolCert(82);

        Application alexApplication = new Application(alex, LocalDateTime.of(2022, 7, 15, 14, 22));
        int alexTotalScore = alexApplication.getTotalScore(alexTestCerts, alexSchoolCert);

        try (Logbook alexLog = new Logbook(alex, 123)) {
            LOGGER.info("New logbook created");
        }

        UniUtils.chooseFaculty(pediatrics, alexApplication, alexTotalScore);
        UniUtils.getCheapest(bsmu);
        UniUtils.welcome(employeesGM);
        UniUtils.drinkBreak(deanGM);
        UniUtils.checkSchedule(secretary, LocalTime.of(12, 30));
    }
}
