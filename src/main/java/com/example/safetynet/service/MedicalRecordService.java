package com.example.safetynet.service;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService implements MedicalrecordServiceInterface {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    private static Logger logger = LogManager.getLogger("MedicalRecordService");


    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordRepository.findAll();
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

    /*sert à calculer l'age selon sa date de naissance*/
    @Override
    public int getAge(String firstname, String lastName) {
        int age = 0;
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastName)) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate birthdate = LocalDate.parse(medicalRecord.getBirthdate(), formatter);
                age = Period.between(birthdate, LocalDate.now()).getYears();
            }
        }
        return age;
    }

    /* donne la liste des médicaments d'une personne*/
    @Override
    public List<String> getMedications(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();
        List<String> medications = new ArrayList<>();

        for (MedicalRecord mr : medicalRecordList) {
            if (mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
                medications = mr.getMedications();
            }
        }
        return medications;
    }

  /* Donne les allergies d'une personne*/
    @Override
    public List<String> getAllergies(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();
        List<String> allergies = new ArrayList<>();

        for (MedicalRecord mr : medicalRecordList) {
            if (mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
                allergies = mr.getAllergies();
            }
        }
        return allergies;
    }
}

