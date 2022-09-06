package com.solvd.university;

import com.solvd.university.doc.*;
import com.solvd.university.impl.ICheck;
import com.solvd.university.people.Person;
import com.solvd.university.people.staff.*;
import com.solvd.university.structure.*;
import com.solvd.university.people.Student;
import com.solvd.university.exception.DataInvalidException;

import com.solvd.university.threads.Connection;
import com.solvd.university.threads.ConnectionPool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.*;
import java.util.stream.Collectors;

public class MainClass {

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        Rector rector = new Rector("Alexey", "Mikhailov", Person.Gender.MALE);
        rector.setStart(LocalDate.of(2011, 5, 2));

        Dean deanGM = new Dean("Ivan", "Ivanov", Person.Gender.MALE);
        Dean deanPed = new Dean("Zinaida", "Zinaidovna", Person.Gender.FEMALE);
        Dean deanDent = new Dean("Mikhail", "Mikhailov", Person.Gender.MALE);
        Dean deanPharm = new Dean("Zukhara", "Karimova", Person.Gender.FEMALE);
        deanGM.setDrinkPreference(Drinks.COFFEE);

        List<Employee> employeesGM = new ArrayList<>();
        employeesGM.add(deanGM);
        employeesGM.add(new Professor("Andrey", "Andreevich", Person.Gender.MALE));
        employeesGM.add(new Professor("Anna", "Annova", Person.Gender.FEMALE));

        Student alex = new Student("Alex", "May", Person.Gender.MALE);
        Student rita = new Student("Rita", "Avdeeva", Person.Gender.FEMALE);
        Student ira = new Student("Irina", "Udina", Person.Gender.FEMALE);
        Student dima = new Student("Dmitry", "Udin", Person.Gender.MALE);

        Secretary secretary = new Secretary("Alisa", "Alisovna", Person.Gender.FEMALE);
        secretary.work(employeesGM.get(1));

        University bsmu = new University("Belarusian State Medical University", LocalDate.of(1921, 1, 1).getYear());
        int dateOfEst = bsmu.getDateOfEstablishment();

        Faculty generalMedicine = new Faculty("General Medicine", 1000);
        generalMedicine.setDean(deanGM);
        generalMedicine.setEmployees(employeesGM);
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

        Consumer<Passport> passportConsumer = x -> LOGGER.info("Passport № " + x.getSerialNumber());
        passportConsumer.accept(alexPassport);

        Supplier<Integer> dateSupplier = () -> LocalDate.now().getYear();

        TestCertificate alexCertBio = new TestCertificate(LocalDate.of(2022, 6, 17),
                TestCertificate.calcCertScore(),
                "Biology");
        TestCertificate alexCertBel = new TestCertificate(LocalDate.of(2022, 6, 10),
                TestCertificate.calcCertScore(),
                "Belarusian");
        TestCertificate alexCertChem = new TestCertificate(LocalDate.of(2022, 6, 20),
                TestCertificate.calcCertScore(),
                "Chemistry");
        List<TestCertificate> alexTestCerts = new ArrayList<>(List.of(alexCertBel, alexCertBio, alexCertChem));

        TestCertificate ritaCertBio = new TestCertificate(LocalDate.of(2022, 6, 17),
                TestCertificate.calcCertScore(),
                "Biology");
        TestCertificate ritaCertBel = new TestCertificate(LocalDate.of(2022, 6, 10),
                TestCertificate.calcCertScore(),
                "Belarusian");
        TestCertificate ritaCertChem = new TestCertificate(LocalDate.of(2022, 6, 20),
                TestCertificate.calcCertScore(),
                "Chemistry");
        List<TestCertificate> ritaTestCerts = new ArrayList<>(List.of(ritaCertBel, ritaCertBio, ritaCertChem));

        Predicate<List<TestCertificate>> isPassed = x -> x.size() == 3;
        ICheck<List<TestCertificate>> isRelevant = cert -> cert.stream()
                .anyMatch(x -> x.getSubject().equals("Biology") || x.getSubject().equals("Chemistry"));

        alex.setPassedTest(isPassed.test(alexTestCerts) && isRelevant.check(alexTestCerts));
        rita.setPassedTest(isPassed.test(ritaTestCerts) && isRelevant.check(ritaTestCerts));
        ira.setPassedTest(false);
        dima.setPassedTest(true);

        SchoolCert alexSchoolCert = new SchoolCert(82);

        Application alexApplication = new Application(alex, LocalDateTime.of(dateSupplier.get(), 7, 15, 14, 22));
        Application ritaApplication = new Application(rita, LocalDateTime.of(dateSupplier.get(), 7, 15, 14, 22));
        Application iraApplication = new Application(ira, LocalDateTime.of(dateSupplier.get(), 7, 15, 14, 22));
        Application dimaApplication = new Application(dima, LocalDateTime.of(dateSupplier.get(), 7, 15, 14, 22));

        ICount scoreCounter = Integer::sum;
        IValidate<TestCertificate> certValidator = x -> x.getDateOfIssue().getYear() == dateSupplier.get();

        alexApplication.setSchoolCert(alexSchoolCert);
        alexApplication.setTestCertificates(alexTestCerts);

        int alexCertScore = alexApplication.getCertScore(alexTestCerts, scoreCounter, certValidator);
        int alexTotalScore = scoreCounter.count(alexCertScore, alexSchoolCert.getCertScore());

        alexApplication.setStatus(Application.ApplicationStatus.SUBMITTED);
        ritaApplication.setStatus(Application.ApplicationStatus.WAITING);
        iraApplication.setStatus(Application.ApplicationStatus.SUBMITTED);
        dimaApplication.setStatus(Application.ApplicationStatus.WAITING);
        UniUtils.checkStatus(alexApplication);
        List<Application> currentApplications = new ArrayList<>();
        currentApplications.add(alexApplication);
        currentApplications.add(ritaApplication);
        currentApplications.add(iraApplication);
        currentApplications.add(dimaApplication);

        PassbookEntry<Integer> alexPassbookHistory = new PassbookEntry<>("History", 9);
        PassbookEntry<String> alexPassbookPhilosophy = new PassbookEntry<>("Philosophy", "passed");

        try (Logbook<Integer> alexLog = new Logbook<>(alex, 123)) {
            List<PassbookEntry<?>> alexPassbook = new ArrayList<>();
            alexPassbook.add(alexPassbookHistory);
            alexPassbook.add(alexPassbookPhilosophy);
        }

        try {
            File text = new File("src/main/resources/textSample.txt");
            String content = FileUtils.readFileToString(text, "UTF-8");
            List<String> contentArray = new ArrayList<>(List.of(StringUtils.split(content)));
            Map<String, Integer> wordsMap = contentArray.stream()
                    .filter(word -> word.length() > 3 && StringUtils.isAlpha(word))
                    .sorted(Comparator.comparing(word -> StringUtils.countMatches(content, word)))
                    .collect(Collectors.toMap(word -> word, word -> StringUtils.countMatches(content, word), (a1, a2) -> a2, LinkedHashMap::new));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        Employee firstEmployee = bsmuFaculties.stream()
                .filter(fc -> fc.getEmployees() != null)
                .flatMap(fc -> fc.getEmployees().stream())
                .peek(LOGGER::info)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No employee found."));

        currentApplications.stream()
                .filter(application -> (Application.ApplicationStatus.WAITING).equals(application.getStatus()))
                .forEach(application -> application.setStatus(Application.ApplicationStatus.PROCESSED));

        List<Student> applicants = currentApplications.stream()
                .map(Application::getStudent)
                .collect(Collectors.toList());

        try {
            Class<Student> studentClass = (Class<Student>) Class.forName("com.solvd.university.people.Student");

            Constructor<Student> studentConstructor = studentClass.getDeclaredConstructor(String.class, String.class, Person.Gender.class);
            Student feofan = studentConstructor.newInstance("Feofan", "Feofanov", Person.Gender.MALE);
            Method greet = studentClass.getDeclaredMethod("greet");
            greet.invoke(feofan);
            Method pass = studentClass.getDeclaredMethod("setPassedTest", boolean.class);
            pass.invoke(feofan, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Connection conn = null;
        ConnectionPool pool = ConnectionPool.getInstance();

        for (int i = 0; i < 10; i++) {
            try {
                conn = pool.getConnection();
                System.out.println(conn);
                Thread.sleep(200);
            }catch (InterruptedException e) {
                LOGGER.error(e);
            } finally {
                if (conn != null) {
                    pool.releaseConnection(conn);
                }
            }
        }

        CompletableFuture<String> asyncHello1 = CompletableFuture.supplyAsync(() -> "World1", EXECUTOR_SERVICE);
        CompletableFuture<String> asyncHello2 = CompletableFuture.supplyAsync(() -> "World2", EXECUTOR_SERVICE);
        CompletableFuture<String> asyncHello3 = CompletableFuture.supplyAsync(() -> "World3", EXECUTOR_SERVICE);
        CompletableFuture<String> asyncHello4 = CompletableFuture.supplyAsync(() -> "World4", EXECUTOR_SERVICE);
        List<CompletableFuture<String>> list = Arrays.asList(asyncHello1, asyncHello2, asyncHello3, asyncHello4);

        EXECUTOR_SERVICE.execute(() -> {
            list.forEach(i -> list.set(list.indexOf(i), i.thenApply(n -> "Hello " + n)));
            list.forEach(i -> LOGGER.info(i.join()));
        });
        EXECUTOR_SERVICE.shutdown();
    }
}