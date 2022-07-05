package com.example.safetynet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
public class MedicalRecord {

    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate birthdate;
    private List<String> medications;
    private List<String> allergies;

    public MedicalRecord(String firstName, String lastName, LocalDate birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public MedicalRecord() {

    }

    public Id getId() {
        return new Id(firstName, lastName);
    }

    public int getAge() {
        /*sert à calculer l'age selon sa date de naissance*/
        if (birthdate == null)
            return -1;

        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    public boolean isMinor() {
        return getAge() <= 18;
    }
}
