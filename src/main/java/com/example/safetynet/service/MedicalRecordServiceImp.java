package com.example.safetynet.service;

import com.example.safetynet.Exception.MedicalRecordNotFoundException;
import com.example.safetynet.dto.Id;
import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepositoryImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImp implements MedicalRecordService {

    @Autowired
    MedicalRecordRepositoryImp medicalRecordRepositoryImp;

    private static Logger logger = LogManager.getLogger("MedicalRecordServiceImp");


    public MedicalRecordServiceImp(MedicalRecordRepositoryImp medicalRecordRepositoryImp) {
        this.medicalRecordRepositoryImp = medicalRecordRepositoryImp;
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepositoryImp.addMedicalRecord(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName) {

        Id id = new Id(firstName, lastName);

        MedicalRecord medicalRecordToUpdate = medicalRecordRepositoryImp.findAMedicalRecordById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException("The medical record is not found"));

        medicalRecordToUpdate.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalRecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalRecord.getAllergies());

        return medicalRecordRepositoryImp.updateMedicalRecord(medicalRecord,id);
    }

    @Override
    public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepositoryImp.deleteByFirstNameAndLastName(firstName, lastName);
        return null;
    }
}

