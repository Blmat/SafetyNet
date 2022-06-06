package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;

import java.util.List;

public interface MedicalrecordServiceInterface {

    List<MedicalRecord> getMedicalRecords();

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName);

    MedicalRecord deleteMedicalRecord(String firstName, String lastName);

    int getAge(String firstname, String lastName);

    // get the medications of a specific person
    List<String> getMedications(String firstName, String lastName);

    // get the allergies of a specific person
    List<String> getAllergies(String firstName, String lastName);
}
