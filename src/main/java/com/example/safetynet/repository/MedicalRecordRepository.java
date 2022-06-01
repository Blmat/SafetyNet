package com.example.safetynet.repository;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord){
        this.dataContainer.getMedicalrecords().add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecordOld, String firstName, String lastName){

        MedicalRecord medicalRecordNew =  findByFirstNameAndLastName(firstName, lastName);
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
}
