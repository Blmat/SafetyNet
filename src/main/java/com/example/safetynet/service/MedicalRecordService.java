package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService implements MedicalrecordServiceInterface {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    private static Logger logger = LogManager.getLogger("MedicalRecordService");


    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName) {
        return medicalRecordRepository.updateMedicalRecord(medicalRecord, firstName, lastName);
    }

    @Override
    public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
        return null;
    }
}

