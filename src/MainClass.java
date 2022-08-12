import doc.*;
import people.Student;
import people.staff.*;
import structure.*;
import exception.DataInvalidException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

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

        List<Employee> employeesGM = new ArrayList<>();
        employeesGM.add(deanGM);
        employeesGM.add(new Professor("Andrey", "Andreevich", "M"));
        employeesGM.add(new Professor("Anna", "Annova", "F"));

        Student alex = new Student("Alex", "May", "M");
        Student rita = new Student("Rita", "Avdeeva", "F");
        Student ira = new Student("Irina", "Udina", "F");
        Student dima = new Student("Dmitry", "Udin", "M");
        alex.setPassedTest(true);
        rita.setPassedTest(true);
        ira.setPassedTest(true);
        dima.setPassedTest(true);

        Secretary secretary = new Secretary("Alisa", "Alisovna", "F");
        secretary.work(employeesGM.get(1));

        University bsmu = new University("Belarusian State Medical University", LocalDate.of(1921, 1, 1).getYear());
        int dateOfEst = bsmu.getDateOfEstablishment();

        Faculty generalMedicine = new Faculty("General Medicine", 1000);
        generalMedicine.setDean(deanGM);
        generalMedicine.setCost(new BigDecimal(3000));
        generalMedicine.setYear(dateOfEst);
        Faculty pediatrics = new Faculty("Pediatrics", 3);
        pediatrics.setDean(deanPed);
        pediatrics.setCost(new BigDecimal(3000));
        pediatrics.setYear(dateOfEst);
        Faculty dentistry = new Faculty("Dentistry", 50);
        dentistry.setDean(deanDent);
        dentistry.setCost(new BigDecimal(4000));
        dentistry.setYear(1931);
        Faculty pharmacology = new Faculty("Pharmacology", 100);
        pharmacology.setDean(deanPharm);
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

        List<Faculty> bsmuFaculties = new ArrayList<>();
        bsmuFaculties.add(generalMedicine);
        bsmuFaculties.add(pediatrics);
        bsmuFaculties.add(dentistry);
        bsmuFaculties.add(pharmacology);
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
        List<TestCertificate> alexTestCerts = new ArrayList<>();
        alexTestCerts.add(alexCertBio);
        alexTestCerts.add(alexCertBel);
        alexTestCerts.add(alexCertChem);

        SchoolCert alexSchoolCert = new SchoolCert(82);

        Application alexApplication = new Application(alex, LocalDateTime.of(2022, 7, 15, 14, 22));
        Application ritaApplication = new Application(rita, LocalDateTime.of(2022, 7, 15, 14, 22));
        Application iraApplication = new Application(ira, LocalDateTime.of(2022, 7, 15, 14, 22));
        Application dimaApplication = new Application(dima, LocalDateTime.of(2022, 7, 15, 14, 22));
        int alexTotalScore = alexApplication.getTotalScore(alexTestCerts, alexSchoolCert);

        PassbookEntry<Integer> alexPassbookHistory = new PassbookEntry<>("History", 9);
        PassbookEntry<String> alexPassbookPhilosophy = new PassbookEntry<>("Philosophy", "passed");

        try (Logbook<Integer> alexLog = new Logbook<>(alex, 123)) {
            List<PassbookEntry<?>> alexPassbook = new ArrayList<>();
            alexPassbook.add(alexPassbookHistory);
            alexPassbook.add(alexPassbookPhilosophy);
            LOGGER.info(alexPassbook);
        }

        Inventory.printInventoryList(alexTestCerts);
        Inventory.printInventoryList(bsmu.getFaculty());

        UniUtils.chooseFaculty(pediatrics, alexApplication, alexTotalScore);
        UniUtils.chooseFaculty(pediatrics, ritaApplication, 310);
        UniUtils.chooseFaculty(pediatrics, iraApplication, 360);
        UniUtils.chooseFaculty(pediatrics, dimaApplication, 356);
        UniUtils.getCheapest(bsmu);
        UniUtils.welcome(employeesGM);
        UniUtils.drinkBreak(deanGM);
        UniUtils.checkSchedule(secretary, LocalTime.of(12, 30));
    }
}
