package com.example.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class Flood {
/*http://localhost:8080/flood/stations?stations=<a list of station_numbers>
Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.*/
    private String firstName;
    private String lastName;
    private Integer age;
    private String phone;
    private List<String> medications;
    private List<String> allergies;


    public Flood() { }

    public Flood(String firstName, String lastName, int age, String phone, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.medications = medications;
        this.allergies = allergies;
    }
}
