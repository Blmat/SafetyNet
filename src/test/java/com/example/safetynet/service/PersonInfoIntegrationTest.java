package com.example.safetynet.service;

import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonInfoIntegrationTest {

    private JsonReaderMock jsonReader;
    public PersonInfo personInfo;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImp(jsonReader);
        FireStationRepository fireStationRepository = new FireStationRepositoryImp(jsonReader);

        personInfo = new PersonInfoImp(personRepository, fireStationRepository, medicalRecordRepository);
    }

    @Test
    @DisplayName("test findStationByAddress method")
    void findStationByAddress() {
        //given
        final var person = new Person("John", "Boyd", "1509 Culver St", null, null, null, null);
        final var medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now(), new ArrayList<>(), new ArrayList<>());
        final var fireStation = new FireStation("1509 Culver St", 3);
        //when
        jsonReader.addPerson(person);
        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        jsonReader.addMedicalRecord(medicalRecord);
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(fireStation);

        final var response = personInfo.findStationByAddress("1509 Culver St");
    }

    @Test
    @DisplayName("Test the method findAddressByStation")
    void findAddressByStationTest() {
        //given
        final var person = new Person("John", "Boyd", "1509 Culver St", null, null, null, null);
        final var medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now(), new ArrayList<>(), new ArrayList<>());
        final var fireStation = new FireStation("1509 Culver St", 3);
        //when
        jsonReader.addPerson(person);
        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        jsonReader.addMedicalRecord(medicalRecord);
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(fireStation);

        final var response = personInfo.findAddressByStation(3);

        //then
        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(response.equals(fireStation.getStation()));
    }

    @Test
    @DisplayName("Test the method findPhoneByStationNumber")
    void createPersonInfoTest() {
        //given
        final var person = new Person("John", "Boyd", "1509 Culver St", "Culver", null, "841-874-6512", null);
        final var medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now().minusYears(30), List.of(), List.of());
        final var fireStation = new FireStation("1509 Culver St", 3);
        //when
        jsonReader.addPerson(person);
        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        jsonReader.addMedicalRecord(medicalRecord);
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(fireStation);
    }

    @Test
    @DisplayName("test getPersonInformation method ")
    void getPersonInformationTest() {

        //GIVEN
        final var address = "1509 Culver St";
        final var lastName = "Boyd";
        final var firstName = "John";

        final var age = 42;
        final var birthday = LocalDate.now().minusYears(age);
        final var person = new Person(firstName, lastName, address, "Culver", "97451", "841-874-6512", "tenz@email.com");
        final var person2 = new Person("Jacob", "Boyd", "1509 Culver St", null, null, "845-555-978", null);
        final var person3 = new Person("Tenley", "Marrack", address, "Culver", "97451", "841-874-6512", "drk@email.com");
        final var medicalRecord = new MedicalRecord(firstName, lastName, birthday, List.of("aznol:350mg\", \"hydrapermazol:100mg"), List.of("nillacilan"));
        final var medicalRecord2 = new MedicalRecord("Jacob", lastName, LocalDate.now().minusYears(19), List.of(), List.of());
        final var medicalRecord3 = new MedicalRecord("Tenley", "Marrack", LocalDate.now().minusYears(85), List.of(), List.of());

        jsonReader.addPerson(person);
        jsonReader.addPerson(person2);
        jsonReader.addPerson(person3);
        jsonReader.addMedicalRecord(medicalRecord);
        jsonReader.addMedicalRecord(medicalRecord2);
        jsonReader.addMedicalRecord(medicalRecord3);

        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .first()
                .isEqualTo(person);

        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .first()
                .isEqualTo(medicalRecord);

        personInfo.getPersonInformation(firstName, lastName); /*Deux personnes doivent être trouvées, car seulement deux on le même nom de famille*/

    }
}
