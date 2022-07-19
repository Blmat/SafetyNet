package com.example.safetynet.service;

import com.example.safetynet.Exception.MedicalRecordNotFoundException;
import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.MedicalRecordRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedicalRecordServiceIntegrationTest {

    private JsonReaderMock jsonReader;

    private MedicalRecordService medicalRecordService;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImp(jsonReader);

        medicalRecordService = new MedicalRecordServiceImp(medicalRecordRepository);
    }

    @Test
    @DisplayName("Test add method")
    void addMethodTest() {
        // GIVEN

        final var medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now().minusYears(32), List.of(), List.of());

        jsonReader.addMedicalRecord(medicalRecord);
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        // WHEN
        final var response = medicalRecordService.addMedicalRecord(medicalRecord);

        // THEN
        assertThat(response)
                .isNotNull();
        assertEquals(response.getFirstName(), medicalRecord.getFirstName());
        assertEquals(response.getLastName(), medicalRecord.getLastName());
    }

    @Test
    @DisplayName("Test update method")
    void updateMethodTest() {
        // GIVEN

        final var medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now().minusYears(32), List.of(), List.of());

        jsonReader.addMedicalRecord(medicalRecord);
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        // WHEN
        final var response = medicalRecordService.updateMedicalRecord(medicalRecord, "John", "Boyd");

        // THEN
        assertThat(response)
                .isNotNull();
        assertEquals(response.getFirstName(), medicalRecord.getFirstName());
        assertEquals(response.getLastName(), medicalRecord.getLastName());
    }

    @Test
    @DisplayName("catch error if the medicalRecord is not found")
    void updateMethodErrorTest() {
        // GIVEN

        final var medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now().minusYears(32), List.of(), List.of());

        jsonReader.addMedicalRecord(medicalRecord);
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        assertThrows(MedicalRecordNotFoundException.class, () -> medicalRecordService.updateMedicalRecord(medicalRecord, "Jacob", "Boyd"));
    }

    @Test
    @DisplayName("Test delete method")
    void deleteMethodTest() {
        // GIVEN

        final var medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now().minusYears(32), List.of(), List.of());

        jsonReader.addMedicalRecord(medicalRecord);
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        // WHEN
        medicalRecordService.deleteMedicalRecord("John", "Boyd");

        //THEN
        assertThat(jsonReader.getDatas().getMedicalRecords())
                .isNotNull()
                .isEmpty();
    }
}