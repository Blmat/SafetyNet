package com.example.safetynet.dto;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import lombok.Getter;

@Getter
public class PersonAggregate {

    private final Person person;
    private final MedicalRecord medicalRecord;


    public PersonAggregate(Person person, MedicalRecord medicalRecord) {
        this.person = person;
        this.medicalRecord = medicalRecord;
    }

    public String getName(){
        return person.getFirstName() + " " + person.getLastName();
    }
}
