package com.example.safetynet.repository;

import com.example.safetynet.exception.MedicalRecordNotFoundException;
import com.example.safetynet.model.Id;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.service.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class MedicalRecordRepositoryImp implements MedicalRecordRepository {

    private final JsonReader jsonReader;

    @Autowired
    public MedicalRecordRepositoryImp(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @Override
    public Stream<MedicalRecord> getAllMedicalRecord() {
        return this.jsonReader.getDatas().getMedicalrecords().stream();
    }

    @Override
    public Optional<MedicalRecord> findAMedicalRecordById(Id id) {
        return getAllMedicalRecord().filter(medicalRecord -> medicalRecord.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<MedicalRecord> findAll() {
        return this.jsonReader.getDatas().getMedicalrecords();
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecordsList = this.jsonReader.getDatas().getMedicalrecords();
        medicalRecordsList.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, Id id) {
        MedicalRecord medicalRecordToUpdate = findAMedicalRecordById(id).orElseThrow(() -> new MedicalRecordNotFoundException(("Medical Record not found whit id = " + id)));
        int index = jsonReader.getDatas().getMedicalrecords().indexOf(medicalRecordToUpdate);
        return jsonReader.getDatas().getMedicalrecords().set(index, medicalRecord);
    }

    @Override
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.jsonReader.getDatas().getMedicalrecords().removeIf(medicalRecord ->
                medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName));
    }
}
