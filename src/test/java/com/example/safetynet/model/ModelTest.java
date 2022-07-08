package com.example.safetynet.model;

import com.example.safetynet.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ModelTest {

    @Test
    // Test the getters/setters and constructor of DataContainer class
    public void dataContainerTest() {
        List<Person> personList = new ArrayList<>();
        List<FireStation> firestationList = new ArrayList<>();
        List<MedicalRecord> medicalRecordList = new ArrayList<>();

        DataContainer dc = new DataContainer(personList, firestationList, medicalRecordList);

        Assertions.assertEquals(firestationList, dc.getFirestations());
        Assertions.assertEquals(personList, dc.getPersons());
        Assertions.assertEquals(medicalRecordList, dc.getMedicalrecords());
    }

    // Test the getters/setters and constructor of Person class
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

        Assertions.assertEquals("John", mr.getFirstName());
        Assertions.assertEquals("Doe", mr.getLastName());
        Assertions.assertEquals(birthdate, mr.getBirthdate());
        Assertions.assertEquals(medication, mr.getMedications());
        Assertions.assertEquals(allergies, mr.getAllergies());
        Assertions.assertEquals(toString, mr.toString());

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

    // Test the getters/setters and constructor of FireAlert class
    @Test
    public void fireAlertTest()  {
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        List<String> stationNumber = new ArrayList<>();
        stationNumber.add(String.valueOf(3));
        FireStationListPerson fireStationListPerson = new FireStationListPerson("John", "Boyd", 38, "841-874-6512",medication, allergies, stationNumber);
        String toString = "FireStationListPerson(firstName=John, lastName=Boyd, age=38, phone=841-874-6512, medications=[], allergies=[], stationNumber=[3])";

        assertEquals("John", fireStationListPerson.getFirstName());
        assertEquals("Boyd", fireStationListPerson.getLastName());
        assertEquals(java.util.Optional.of(38), java.util.Optional.of(fireStationListPerson.getAge()));
        assertEquals("841-874-6512", fireStationListPerson.getPhone());
        assertEquals(medication, fireStationListPerson.getMedications());
        assertEquals(allergies, fireStationListPerson.getAllergies());
        assertEquals(toString, fireStationListPerson.toString());
    }

    // Test the getters/setters and constructor of Flood class
    @Test
    public void floodTest() {
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        Flood flood = new Flood("John", "Boyd", 38, "841-874-6512", medication, allergies);
        String toString = "Flood(firstName=John, lastName=Boyd, age=38, phone=841-874-6512, medications=[], allergies=[])";

        assertEquals("John", flood.getFirstName());
        assertEquals("Boyd", flood.getLastName());
        assertEquals(Optional.of(38), Optional.of(flood.getAge()));
        assertEquals("841-874-6512", flood.getPhone());
        assertEquals(medication,medication);
        assertEquals(allergies,allergies);
        assertEquals(medication, flood.getMedications());
        assertEquals(allergies, flood.getAllergies());
        assertEquals(toString, flood.toString());
    }

    // Test the getters/setters and constructor of HouseHold class
    @Test
    public void householdTest() {
        List<Flood> flood = new ArrayList<>();
        Household hh = new Household("000", flood);
        String toString = "Household(address=000, flood=[])";

        Household h = new Household();
        h.setAddress("000");
        h.setFlood(flood);

        assertEquals("000", hh.getAddress());
        assertEquals(flood.size(), hh.getFlood().size());
        assertEquals("000", h.getAddress());
        assertEquals(toString, hh.toString());
    }

    // Test the getters/setters and constructor of PersonCovered class
    @Test
    public void personCoveredTest() {
        PersonCovered pc = new PersonCovered("John", "Boyd", "1509 Culver St", "841-874-6512");
        String toString = "PersonCovered(firstName=John, lastName=Boyd, address=1509 Culver St, phone=841-874-6512)";

        Assertions.assertEquals("John", pc.getFirstName());
        Assertions.assertEquals("Boyd", pc.getLastName());
        Assertions.assertEquals("1509 Culver St", pc.getAddress());
        Assertions.assertEquals("841-874-6512", pc.getPhone());
        Assertions.assertEquals(toString, pc.toString());
    }

    // Test the getters/setters and constructor of PersonInfo class
    @Test
    public void personInfoTest() {
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        PersonInfo pi = new PersonInfo("John", "Boyd", "1509 Culver St", 38, "jaboyd@email.com", medication, allergies);
        String toString = "PersonInfo(firstName=John, lastName=Boyd, address=1509 Culver St, age=38, email=jaboyd@email.com, medications=[], allergies=[])";

        Assertions.assertEquals("John", pi.getFirstName());
        Assertions.assertEquals("Boyd", pi.getLastName());
        Assertions.assertEquals("1509 Culver St", pi.getAddress());
        Assertions.assertEquals(Optional.of(38), Optional.of(pi.getAge()));
        Assertions.assertEquals("jaboyd@email.com", pi.getEmail());
        Assertions.assertEquals(medication, pi.getMedications());
        Assertions.assertEquals(allergies, pi.getAllergies());
        Assertions.assertEquals(toString, pi.toString());
    }

    // Test the getters/setters and constructor of StationCoverage class
    @Test
    public void stationCoverageTest() {
        List<PersonCovered> personCovered = new ArrayList<>();
        FireStationCoveragePerson fireStationCoveragePerson = new FireStationCoveragePerson(1, 1, personCovered);
        String toString = "FireStationCoveragePerson(station=null, adults=1, child=1, personsCovered=[])";

        FireStationCoveragePerson s = new FireStationCoveragePerson();
        s.setAdults(1);
        s.setChild(1);
        s.setPersonsCovered(personCovered);

        Assertions.assertEquals(1, fireStationCoveragePerson.getChild());
        Assertions.assertEquals(1, fireStationCoveragePerson.getAdults());
        Assertions.assertEquals(personCovered.size(), fireStationCoveragePerson.getPersonsCovered().size());
        Assertions.assertEquals(1, s.getChild());
        Assertions.assertEquals(1, s.getAdults());
        Assertions.assertEquals(toString, fireStationCoveragePerson.toString());
    }

}
