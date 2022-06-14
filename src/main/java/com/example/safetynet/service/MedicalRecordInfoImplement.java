package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordInfoImplement implements MedicalRecordInfoInterface{

    public MedicalRecordInfoImplement(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }
    private MedicalRecordRepository medicalRecordRepository;


    // get the medications of a specific person
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

    // get the allergies of a specific person
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
