package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.mock.JsonReaderMock;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.MedicalRecordRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MedicalRecordInfoIntegrationTest {

    private JsonReaderMock jsonReader;

    private MedicalRecordInfo medicalRecordInfo;

    @BeforeEach
    void init() {
        jsonReader = new JsonReaderMock();
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepositoryImp(jsonReader);

        medicalRecordInfo = new MedicalRecordInfoImp(medicalRecordRepository);
    }

    @Test
    @DisplayName("No MedicalRecord at this name")
    void noChildFoundAtThisAddress() {
        //GIVEN
        final var firstName = "John";
        final var lastName = "Boyd";

        //WHEN
        final var response = medicalRecordInfo.getMedications(firstName, lastName);

        //THEN
        assertThat(response)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("One medicalRecord found for one person")
    void getMedicationsTest() {
        final var firstName = "John";
        final var lastName = "Boyd";

        final var birthday = LocalDate.now().minusYears(20);
        final var medicalRecord = new MedicalRecord(firstName, lastName, birthday, List.of("aznol:350mg\", \"hydrapermazol:100mg"), List.of("nillacilan"));

        jsonReader.addMedicalRecord(medicalRecord);

        assertThat(jsonReader.getDatas().getMedicalrecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        //WHEN
        final var response = medicalRecordInfo.getMedications(firstName, lastName);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty();
        assertThat(medicalRecord.getMedications().equals("aznol:350mg\", \"hydrapermazol:100mg"));
    }

    @Test
    @DisplayName("One Allergie found for one person")
    void getAllergiesTest() {
        final var firstName = "John";
        final var lastName = "Boyd";

        final var birthday = LocalDate.now().minusYears(20);
        final var medicalRecord = new MedicalRecord(firstName, lastName, birthday, List.of("aznol:350mg\", \"hydrapermazol:100mg"), List.of("nillacilan"));

        jsonReader.addMedicalRecord(medicalRecord);

        assertThat(jsonReader.getDatas().getMedicalrecords())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .first()
                .isEqualTo(medicalRecord);

        //WHEN
        final var response = medicalRecordInfo.getAllergies(firstName, lastName);

        //THEN
        assertThat(response)
                .isNotNull()
                .isNotEmpty();
        assertThat(medicalRecord.getAllergies().equals("nillacilan"));
        System.out.println(response);
    }
}