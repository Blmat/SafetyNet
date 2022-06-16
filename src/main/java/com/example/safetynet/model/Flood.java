package com.example.safetynet.model;

import java.util.List;

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

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() { return this.age; }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getMedications() { return medications; }
    public void setMedications(List<String> medications) { this.medications = medications; }

    public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String toString() {
        return "[ firstname= " + firstName + ", lastName = " + lastName + ", age = " + age + ", phone number = " + phone + ", medications = " + medications + ", allergies = " + allergies + "]";
    }
}
