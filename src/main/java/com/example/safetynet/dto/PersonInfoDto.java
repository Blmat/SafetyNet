package com.example.safetynet.dto;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class PersonInfoDto {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final int age;
    private final String email;
    private final List<String> medications;
    private final List<String> allergies;

    public PersonInfoDto(Person person, Optional<MedicalRecord> medicalRecordOptional) {

        MedicalRecord medicalRecord = medicalRecordOptional.orElseThrow(() -> new IllegalArgumentException("Medical Record is empty"));

        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.age = medicalRecord.getAge();
        this.email = person.getEmail();
        this.medications = medicalRecord.getMedications();
        this.allergies = medicalRecord.getAllergies();
    }
}
