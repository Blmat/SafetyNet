package com.example.safetynet.repository;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.Id;
import com.example.safetynet.model.MedicalRecord;
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

        MedicalRecord medicalRecordNew = findAMedicalRecordById(Id id);
        medicalRecordNew.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordNew.setMedications(medicalRecord.getMedications());
        medicalRecordNew.setAllergies(medicalRecord.getAllergies());

        return dataContainer.getMedicalrecords().set(dataContainer.getMedicalrecords()
                .indexOf(findAMedicalRecordById(id)), medicalRecordNew);
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.dataContainer.getMedicalrecords().removeIf(medicalRecord ->
                medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName));
    }
}
