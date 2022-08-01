package com.example.safetynet.service;

import com.example.safetynet.exception.MedicalRecordNotFoundException;
import com.example.safetynet.model.Id;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MedicalRecordServiceImp implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImp(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        log.info("MedicalRecord added");
        return medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName) {

        Id id = new Id(firstName, lastName);

        MedicalRecord medicalRecordToUpdate = medicalRecordRepository.findAMedicalRecordById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException("The medical record is not found"));

        medicalRecordToUpdate.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalRecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalRecord.getAllergies());

        return medicalRecordRepository.updateMedicalRecord(medicalRecord,id);
    }

    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        log.info("MedicalRecord to delete" + firstName+" " + lastName);
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }
}

