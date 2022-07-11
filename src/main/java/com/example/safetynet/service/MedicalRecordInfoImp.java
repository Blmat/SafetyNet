package com.example.safetynet.service;

import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordInfoImp implements MedicalRecordInfo {

    public MedicalRecordInfoImp(MedicalRecordRepositoryImp medicalRecordRepositoryImp) {
        this.medicalRecordRepositoryImp = medicalRecordRepositoryImp;
    }
    private MedicalRecordRepositoryImp medicalRecordRepositoryImp;


    // get the medications of a specific person
    @Override
    public List<String> getMedications(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordList = medicalRecordRepositoryImp.findAll();
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
        List<MedicalRecord> medicalRecordList = medicalRecordRepositoryImp.findAll();
        List<String> allergies = new ArrayList<>();

        for(MedicalRecord mr: medicalRecordList) {
            if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
                allergies = mr.getAllergies();
            }
        }
        return allergies;
    }
}
