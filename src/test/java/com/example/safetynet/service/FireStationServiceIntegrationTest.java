package com.example.safetynet.service;

import com.example.safetynet.exception.FireStationNotFoundException;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.FireStationRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FireStationServiceIntegrationTest {

    private JsonReaderMock jsonReader;
    private FireStationService fireStationService;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        FireStationRepository fireStationRepository = new FireStationRepositoryImp(jsonReader);

        fireStationService = new FireStationServiceImp(fireStationRepository);
    }

    @Test
    @DisplayName("Test add method")
    void addMethodTest() {
        // GIVEN
        final var address = "1509 Culver St";

        final var fireStation = new FireStation(address, 2);

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(fireStation);

        // WHEN
        final var response = fireStationService.addFireStation(fireStation);

        // THEN
        assertThat(response)
                .isNotNull();
        assertEquals(response.getAddress(), fireStation.getAddress());
        assertEquals(response.getStation(), fireStation.getStation());

    }

    private void assertEquals(Integer station, Integer station1) {
    }

    private void assertEquals(String address, String address1) {
    }

    @Test
    @DisplayName("test update method")
    void updateFireStationTest() {

        final var fireStation = new FireStation("1509 Culver St", 3);

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        // WHEN
        fireStationService.updateFireStation(fireStation, "1509 Culver St");

        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }
    @Test
    @DisplayName("catch error if the fireStation is not found")
    void updateMethodErrorTest() {
        // GIVEN

        final var fireStation = new FireStation("1509 Culver St", 3);

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertThrows(FireStationNotFoundException.class, () -> fireStationService.updateFireStation(fireStation,"1000 Culver St"));
    }
    @Test
    @DisplayName("Test delete method")
    void deleteMethodTest() {

        final var fireStation = new FireStation("1509 Culver St", 3);

        jsonReader.addFireStation(fireStation);
        assertThat(jsonReader.getDatas().getFireStations())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        // WHEN
        fireStationService.deleteFireStationByAddress("1509 Culver St");

        assertThat(jsonReader.getDatas().getFireStations())
                .isEmpty();

    }
}