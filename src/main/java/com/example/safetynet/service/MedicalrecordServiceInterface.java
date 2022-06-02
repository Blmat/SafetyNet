package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;

import java.util.List;

public interface MedicalrecordServiceInterface {

    List<MedicalRecord> getMedicalRecords();

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName);

    MedicalRecord deleteMedicalRecord(String firstName, String lastName);
}
