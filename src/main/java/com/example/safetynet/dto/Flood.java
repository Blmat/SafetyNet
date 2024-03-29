package com.example.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class Flood {
    /**<a href="http://localhost:8080/flood/stations?stations=">...</a><a list of station_numbers>
     Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
     personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
     faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.*/
    private String firstName;
    private String lastName;
    private String phone;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;

    public Flood(PersonAggregate personAggregate) {
        firstName = personAggregate.getPerson().getFirstName();
        lastName = personAggregate.getPerson().getLastName();
        phone = personAggregate.getPerson().getPhone();
        age = personAggregate.getMedicalRecord().getAge();
        medications = personAggregate.getMedicalRecord().getMedications();
        allergies = personAggregate.getMedicalRecord().getAllergies();
    }
}
