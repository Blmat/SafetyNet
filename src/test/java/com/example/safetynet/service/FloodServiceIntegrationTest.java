//package com.example.safetynet.service;
//
//import com.example.safetynet.mock.JsonReaderMock;
//import com.example.safetynet.repository.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class FloodServiceIntegrationTest {
//
//    private JsonReaderMock jsonReader;
//    private FloodService floodService;
//
//    @BeforeEach
//    void init() {
//        jsonReader = new JsonReaderMock();
//        PersonRepository personRepository = new PersonRepositoryImp(jsonReader);
//        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImp(jsonReader);
//        FireStationRepository fireStationRepository = new FireStationRepositoryImp(jsonReader);
//
//        floodService = new FloodServiceImp(personRepository, medicalRecordRepository,fireStationRepository);
//
//    }
//
//    @Test
//    @DisplayName("No children at this address")
//    void  getHouseAttachedToFireStationTest() {
//        //GIVEN
//        final var station = 3;
//
//        //WHEN
//        final var response = floodService.getHouseAttachedToFireStation(station);
//
//        //THEN
//        assertThat(response)
//                .isNotNull()
//                .isEmpty();
//    }
//}