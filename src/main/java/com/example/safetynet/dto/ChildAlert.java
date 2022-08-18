package com.example.safetynet.dto;

import com.example.safetynet.model.MedicalRecord;
import lombok.Data;

import java.util.List;

@Data
public class ChildAlert {

    private String firstName;
    private String lastName;
    private int age;
    private List<String> family;


    public ChildAlert(MedicalRecord medicalRecord, List<String> family) {
        this.firstName = medicalRecord.getFirstName();
        this.lastName =  medicalRecord.getLastName();
        this.age = medicalRecord.getAge();
        this.family = family;
    }
}
