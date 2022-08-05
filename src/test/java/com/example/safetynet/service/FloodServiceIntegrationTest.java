package com.example.safetynet.service;

import com.example.safetynet.exception.MedicalRecordNotFoundException;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FloodServiceIntegrationTest {

    private JsonReaderMock jsonReader;

    private FloodService floodService;


    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImp(jsonReader);
        FireStationRepository fireStationRepository = new FireStationRepositoryImp(jsonReader);

        floodService = new FloodServiceImp(personRepository, medicalRecordRepository, fireStationRepository);
    }


    @Test
    @DisplayName("test de la methode getHouseAttachedToFireStation")
    void getHouseAttachedToFireStationTest() {
        final var firstName = "John";
        final var lastName = "Boyd";
        final var birthdate = LocalDate.of(1982, 3, 6);


        final var person = new Person(firstName, lastName, "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        final var medicalRecord = new MedicalRecord(firstName, lastName, birthdate, List.of(), List.of());
        final var fireStation = new FireStation("1509 Culver St", 3);

        jsonReader.addFireStation(fireStation);
        jsonReader.addPerson(person);
        jsonReader.addMedicalRecord(medicalRecord);

        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(fireStation);

        floodService.getHouseAttachedToFireStation(3);

    }

    @Test
    @DisplayName("Test pour capter MedicalrecordNotFound ")
    void MedicalRecordNotFoundExceptionTest() {
        final var firstName = "John";
        final var lastName = "Boyd";
        final var birthdate = LocalDate.of(1982, 3, 6);


        final var person = new Person(firstName, lastName, "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        final var medicalRecord = new MedicalRecord("Jacob", lastName, birthdate, List.of(), List.of());
        final var fireStation = new FireStation("1509 Culver St", 3);


        jsonReader.addPerson(person);
        jsonReader.addMedicalRecord(medicalRecord);
        jsonReader.addFireStation(fireStation);

        assertThat(jsonReader.getDatas().getPersons())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(person);

        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(fireStation);

        assertThrows(MedicalRecordNotFoundException.class, () -> floodService.getHouseAttachedToFireStation(3));

    }
}