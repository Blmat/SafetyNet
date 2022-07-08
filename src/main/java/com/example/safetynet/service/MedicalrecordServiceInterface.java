package com.example.safetynet.service;

import com.example.safetynet.dto.MedicalRecord;

public interface MedicalrecordServiceInterface {

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName);

    MedicalRecord deleteMedicalRecord(String firstName, String lastName);
}
