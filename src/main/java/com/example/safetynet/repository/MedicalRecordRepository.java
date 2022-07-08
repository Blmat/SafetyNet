package com.example.safetynet.repository;

import com.example.safetynet.dto.Id;
import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.service.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class MedicalRecordRepository {

    private final DataContainer dataContainer;

    @Autowired
    public MedicalRecordRepository(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public Stream<MedicalRecord> getAllMedicalRecord() {
        return this.dataContainer.getMedicalrecords().stream();
    }

    public Optional<MedicalRecord> findAMedicalRecordById(Id id) {
        return getAllMedicalRecord().filter(medicalRecord -> medicalRecord.getId().equals(id))
                .findFirst();
    }


    public List<MedicalRecord> findAll() {
        return this.dataContainer.getMedicalrecords();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecordsList = this.dataContainer.getMedicalrecords();
        medicalRecordsList.add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, Id id) {
        MedicalRecord medicalRecordToUpdate = findAMedicalRecordById(id).orElseThrow();
        int index= dataContainer.getMedicalrecords().indexOf(medicalRecordToUpdate);
        return dataContainer.getMedicalrecords().set(index, medicalRecord);
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.dataContainer.getMedicalrecords().removeIf(medicalRecord ->
                medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName));
    }
}
