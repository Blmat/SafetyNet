package com.example.safetynet.service;

import com.example.safetynet.Exception.MedicalRecordNotFoundException;
import com.example.safetynet.dto.Id;
import com.example.safetynet.dto.MedicalRecord;
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

        Id id = new Id(firstName, lastName);

        MedicalRecord medicalRecordToUpdate =medicalRecordRepository.findAMedicalRecordById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException(id));

        medicalRecordToUpdate.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalRecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalRecord.getAllergies());

        return medicalRecordRepository.updateMedicalRecord(medicalRecord,id);
    }

    @Override
    public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
        return null;
    }
}

