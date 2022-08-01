package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;

public interface MedicalRecordService {

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName);

    void deleteMedicalRecord(String firstName, String lastName);
}
