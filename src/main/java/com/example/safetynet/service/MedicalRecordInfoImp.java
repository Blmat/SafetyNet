package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordInfoImp implements MedicalRecordInfo {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordInfoImp(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /** obtenir la liste des médicaments d'une personne spécifique */
    @Override
    public List<String> getMedications(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();
        List<String>  medications = new ArrayList<>();

        for(MedicalRecord mr: medicalRecordList) {
            if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
                medications = mr.getMedications();
            }
        }
        return medications;
    }

    /** obtenir la liste des allergies d'une personne spécifique */
    @Override
    public List<String> getAllergies(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();
        List<String> allergies = new ArrayList<>();

        for(MedicalRecord mr: medicalRecordList) {
            if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
                allergies = mr.getAllergies();
            }
        }
        return allergies;
    }
}
