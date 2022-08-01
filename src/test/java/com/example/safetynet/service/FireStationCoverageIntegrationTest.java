package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FireStationCoverageIntegrationTest {

    private JsonReaderMock jsonReader;

    private FireStationCoverage fireStationCoverage;


    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);
        FireStationRepository fireStationRepository = new FireStationRepositoryImp(jsonReader);

        fireStationCoverage = new FireStationCoverageImp(personRepository, fireStationRepository);
    }

    @Test
    @DisplayName("test getPersonsCoverageByStationNumber method and try to find one person")
    void getPersonsCoverageByStationNumberTest() {

        //GIVEN
        final var address = "1509 Culver St";
        final var firstName = "John";
        final var lastName = "Boyd";
        final var  birthdate =  LocalDate.of(1982,03,06);


        final var person = new Person(firstName, lastName, address, "Culver", "97451", "841-874-6512", "jaboyd@email.com");
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

        //WHEN
         fireStationCoverage.getPersonsCoverageByStationNumber(3);
    }



    @Test
    @DisplayName("No person found at this address")
    void getPersonsByAddressTest() {

        //GIVEN
        final var address = "1509 Culver st";

        //WHEN
        final var response = fireStationCoverage.getPersonsByAddress(address);

        //THEN
        assertThat(response)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("No fireStation address found at this station number")
    void getFireStationAddressByStationNumber() {

        //GIVEN
        final var station = 3;
        final var fireStation = new FireStation("1509 Culver St", station);

        assertThat(fireStation.getAddress().equals("1509 Culver St"));
        assertThat(fireStation.getStation().equals(station));

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(fireStation);

        //WHEN
        final var response = fireStationCoverage.getFireStationAddressByStationNumber(station);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

    }

    @Test
    @DisplayName("One person found at this address")
    void personsByAddressTest() {
        //GIVEN
        final var address = "1509 Culver St";
        final var firstName = "John";
        final var lastName = "Boyd";
       final var  birthdate =  LocalDate.of(1982,03,06);


        final var person = new Person(firstName, lastName, address, "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        final var medicalRecord = new MedicalRecord(firstName, lastName, birthdate, List.of(), List.of());
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

        //WHEN
        final var response = fireStationCoverage.getPersonsByAddress(address);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .satisfies(f -> {
                    assertThat(f.getFirstName()).hasToString(person.getFirstName());
                    assertThat(f.getLastName()).hasToString(person.getLastName());
                    assertThat(f.getAge()).isEqualTo(medicalRecord.getAge());
                    assertThat(f.getPhone()).isEqualTo(person.getPhone());
                    assertThat(f.getMedications()).isEqualTo(medicalRecord.getMedications());
                    assertThat(f.getAllergies()).isEqualTo(medicalRecord.getAllergies());
                    assertThat(f.getStationNumber()).isEqualTo(medicalRecord.getAllergies());
                });
    }
}