package com.example.safetynet.service;

import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.dto.Person;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.FireStationRepositoryImp;
import com.example.safetynet.repository.PersonRepository;
import com.example.safetynet.repository.PersonRepositoryImp;
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
    @DisplayName("One person found at this address")
    void onePersonFoundAtThisAddress() {
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