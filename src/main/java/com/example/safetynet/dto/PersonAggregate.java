package com.example.safetynet.dto;

import lombok.Getter;

@Getter
public class PersonAggregate {

    private Person person;
    private MedicalRecord medicalRecord;


    public PersonAggregate(Person person, MedicalRecord medicalRecord) {
        this.person = person;
        this.medicalRecord = medicalRecord;
    }
}
