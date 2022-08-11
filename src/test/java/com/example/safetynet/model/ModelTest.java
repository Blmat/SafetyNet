package com.example.safetynet.model;

import com.example.safetynet.dto.ChildAlert;
import com.example.safetynet.dto.PersonCovered;
import com.example.safetynet.dto.PersonInfoDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    // Test the getters/setters and constructor of DataContainer class
    public void dataContainerTest() {
        List<Person> personList = new ArrayList<>();
        List<FireStation> firestationList = new ArrayList<>();
        List<MedicalRecord> medicalRecordList = new ArrayList<>();

        DataContainer dc = new DataContainer(personList, firestationList, medicalRecordList);

        assertEquals(firestationList, dc.getFirestations());
        assertEquals(personList, dc.getPersons());
        assertEquals(medicalRecordList, dc.getMedicalrecords());
    }

    // Test the getters/setters and constructor of PersonInt class
    @Test
    public void personTest() {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        String personTest = "Person(firstName=John, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com)";

        assertEquals("John", person.getFirstName());
        assertEquals("Boyd", person.getLastName());
        assertEquals("1509 Culver St", person.getAddress());
        assertEquals("Culver", person.getCity());
        assertEquals("97451", person.getZip());
        assertEquals("841-874-6512", person.getPhone());
        assertEquals("jaboyd@email.com", person.getEmail());
        assertEquals(personTest, person.toString());
    }

    // Test the getters/setters and constructor of Firestation class
    @Test
    public void fireStationTest() {
        FireStation fs = new FireStation("1509 Culver St", 3);
        String stationtTest = "FireStation(address=1509 Culver St, station=3)";

        assertEquals("1509 Culver St", fs.getAddress());
        assertEquals("3", fs.getStation().toString());
        assertEquals(stationtTest, fs.toString());
    }

    // Test the getters/setters and constructor of MedicalRecord class
    @Test
    public void medicalRecordTest() {
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        LocalDate birthdate = LocalDate.of(1984,06,03);
        MedicalRecord mr = new MedicalRecord("John", "Doe", birthdate, medication, allergies);
        String toString = "MedicalRecord(firstName=John, lastName=Doe, birthdate=1984-06-03, medications=[], allergies=[])";

        assertEquals("John", mr.getFirstName());
        assertEquals("Doe", mr.getLastName());
        assertEquals(birthdate, mr.getBirthdate());
        assertEquals(medication, mr.getMedications());
        assertEquals(allergies, mr.getAllergies());
        assertEquals(toString, mr.toString());

    }

    // Test the getters/setters and constructor of ChildAlert class
    @Test
    public void childAlertTest() {
        List<String> family = new ArrayList<>();
        family.add("Boyd");
        ChildAlert childAlert = new ChildAlert("Tenley", "Boyd", 10, family);
        String toString = "ChildAlert(firstName=Tenley, lastName=Boyd, age=10, family=[Boyd])";

        assertEquals("Tenley", childAlert.getFirstName());
        assertEquals("Boyd", childAlert.getLastName());
        assertEquals(family, childAlert.getFamily());
        assertEquals(Optional.of(10), Optional.of(childAlert.getAge()));
        assertEquals(toString, childAlert.toString());
    }

    // Test the getters/setters and constructor of PersonCovered class
    @Test
    public void personCoveredTest() {
        PersonCovered pc = new PersonCovered("John", "Boyd", "1509 Culver St", "841-874-6512");
        String toString = "PersonCovered(firstName=John, lastName=Boyd, address=1509 Culver St, phone=841-874-6512, adult=0, child=0)";

        assertEquals("John", pc.getFirstName());
        assertEquals("Boyd", pc.getLastName());
        assertEquals("1509 Culver St", pc.getAddress());
        assertEquals("841-874-6512", pc.getPhone());
        assertEquals(toString, pc.toString());
    }

    // Test the getters/setters and constructor of PersonInfoDto class
    @Test
    public void personInfoTest() {
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        PersonInfoDto pi = new PersonInfoDto("John", "Boyd", "1509 Culver St", 38, "jaboyd@email.com", medication, allergies);
        String toString = "PersonInfoDto(firstName=John, lastName=Boyd, address=1509 Culver St, age=38, email=jaboyd@email.com, medications=[], allergies=[])";

        assertEquals("John", pi.getFirstName());
        assertEquals("Boyd", pi.getLastName());
        assertEquals("1509 Culver St", pi.getAddress());
        assertEquals(Optional.of(38), Optional.of(pi.getAge()));
        assertEquals("jaboyd@email.com", pi.getEmail());
        assertEquals(medication, pi.getMedications());
        assertEquals(allergies, pi.getAllergies());
        assertEquals(toString, pi.toString());
    }
}
