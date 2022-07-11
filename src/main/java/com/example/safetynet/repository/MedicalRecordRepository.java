package com.example.safetynet.repository;

import com.example.safetynet.dto.Id;
import com.example.safetynet.dto.MedicalRecord;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface MedicalRecordRepository {

    Stream<MedicalRecord> getAllMedicalRecord();

    Optional<MedicalRecord> findAMedicalRecordById(Id id);

    List<MedicalRecord> findAll();

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, Id id);

    MedicalRecord deleteByFirstNameAndLastName(String firstName, String lastName);

    }
