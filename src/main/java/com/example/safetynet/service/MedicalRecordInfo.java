package com.example.safetynet.service;

import java.util.List;

public interface MedicalRecordInfo {
    List<String> getMedications(String firstName, String lastName);

    List<String>  getAllergies(String firstName, String lastName);
}
