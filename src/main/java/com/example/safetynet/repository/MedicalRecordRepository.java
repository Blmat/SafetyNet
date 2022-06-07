package com.example.safetynet.repository;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private final DataContainer dataContainer;

    @Autowired
    public MedicalRecordRepository(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }


    public List<MedicalRecord> findAll() {
        return this.dataContainer.getMedicalrecords();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        this.dataContainer.getMedicalrecords().add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecordOld, String firstName, String lastName) {

        MedicalRecord medicalRecordNew = findByFirstNameAndLastName(firstName, lastName);
        medicalRecordNew.setBirthdate(medicalRecordOld.getBirthdate());
        medicalRecordNew.setMedications(medicalRecordOld.getMedications());
        medicalRecordNew.setAllergies(medicalRecordOld.getAllergies());

        return dataContainer.getMedicalrecords().set(dataContainer.getMedicalrecords().indexOf(findByFirstNameAndLastName(firstName, lastName)), medicalRecordNew);
    }

    public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {
        return this.dataContainer.getMedicalrecords().stream()
                .filter(medicalRecord -> (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName))).findAny().orElseThrow();
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.dataContainer.getMedicalrecords().removeIf(medicalRecord ->
                medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName));
    }

    /*sert à calculer l'age selon sa date de naissance*/
    public int getAge(String firstname, String lastName) {
        int age = 0;
        List<MedicalRecord> medicalRecordList = dataContainer.getMedicalrecords();
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastName)) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate birthdate = LocalDate.parse(medicalRecord.getBirthdate(), formatter);
                age = Period.between(birthdate, LocalDate.now()).getYears();
            }
        }
        return age;
    }

    /*Pour avoir une liste des médicaments pris par une personne*/
    public List<String> getMedications(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordList = dataContainer.getMedicalrecords();
        List<String> medications = new ArrayList<>();

        for (MedicalRecord mr : medicalRecordList) {
            if (mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
                medications = mr.getMedications();
            }
        }
        return medications;
    }

    /*Liste des allergies d'une personne*/
    public List<String> getAllergies(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordList = dataContainer.getMedicalrecords();
        List<String> allergies = new ArrayList<>();

        for (MedicalRecord mr : medicalRecordList) {
            if (mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
                allergies = mr.getAllergies();
            }
        }
        return allergies;
    }
}
