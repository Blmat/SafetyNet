package com.example.safetynet.repository;

import com.example.safetynet.model.Id;
import com.example.safetynet.model.MedicalRecord;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface MedicalRecordRepository {

    Stream<MedicalRecord> getAllMedicalRecord();

    Optional<MedicalRecord> findAMedicalRecordById(Id id);

    List<MedicalRecord> findAll();

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, Id id);

    void deleteByFirstNameAndLastName(String firstName, String lastName);

    }
