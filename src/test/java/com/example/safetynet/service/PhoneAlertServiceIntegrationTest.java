package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.Person;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.FireStationRepositoryImp;
import com.example.safetynet.repository.PersonRepository;
import com.example.safetynet.repository.PersonRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneAlertServiceIntegrationTest {

    private JsonReaderMock jsonReader;
    private PhoneAlertService phoneAlertService;

    private FireStationCoverage fireStationCoverage;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);
        FireStationRepository fireStationRepository = new FireStationRepositoryImp(jsonReader);
        phoneAlertService = new PhoneAlertServiceImp(fireStationRepository, personRepository);
    }

//    @Test
//    @DisplayName("test getPhoneNumberByCoverage method")
//    void getPhoneNumberByCoverageTest() {
//
//        final var person = new Person("John", "Boyd", "1509 Culver St", null, null, "841-874-6512", null);
//        final var fireStation = new FireStation("1509 Culver St", 3);
//        final var fireStationCoverage = new MedicalRecord();
//
//        jsonReader.addPerson(person);
//        assertThat(jsonReader.getDatas().getPersons())
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1)
//                .first()
//                .isEqualTo(person);
//
//        jsonReader.addFireStation(fireStation);
//        assertThat(jsonReader.getDatas().getFireStations())
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1)
//                .first()
//                .isEqualTo(fireStation);
//
//        // WHEN
//        final var response = phoneAlertService.getPhoneNumberByCoverage(3);
//    }

}