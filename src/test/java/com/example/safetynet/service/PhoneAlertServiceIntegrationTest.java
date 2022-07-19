package com.example.safetynet.service;

import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.*;
import org.junit.jupiter.api.BeforeEach;

class PhoneAlertServiceIntegrationTest {

    private JsonReaderMock jsonReader;
    private PhoneAlertService phoneAlertService;

    private FireStationCoverage fireStationCoverage;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);
        FireStationRepository fireStationRepository = new FireStationRepositoryImp(jsonReader);
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImp(jsonReader);

        phoneAlertService = new PhoneAlertServiceImp(fireStationCoverage,personRepository);
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