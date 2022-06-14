package com.example.safetynet.model;

import java.util.List;

/*http://localhost:8080/fire?address=<address>
Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
de pompiers la desservante. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
médicaux (médicaments, posologie et allergies) de chaque personne*/

public class FireStationInfoPerson {

    private String firstName;

    private String lastName;

    private Integer age;

    private String phone;

    private List<String> medications;

    private List<String> allergies;

    private List<String> stationNumber;

    public FireStationInfoPerson() {
    }

    public FireStationInfoPerson(String firstName, String lastName, int age, String phone, List<String> medications, List<String> allergies, List<String> stationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.medications = medications;
        this.allergies = allergies;
        this.stationNumber = stationNumber;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getMedications() {
        return medications;
    }
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getStationNumber() { return stationNumber;}
    public void setStationNumber(List<String> stationNumber) { this.stationNumber = stationNumber; }

    @Override
    public String toString() {
        return "Person: firstname= " + firstName + ", lastName = " + lastName + ", age = " + age + ", phone number = " + phone + ", medications = " + medications + ", allergies = " + allergies + ", station number = " + stationNumber;
    }
}
