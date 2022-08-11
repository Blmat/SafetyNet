package com.example.safetynet.dto;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import lombok.Data;

import java.util.List;

/*http://localhost:8080/fire?address=<address>
Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
de pompiers la desservante. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
médicaux (médicaments, posologie et allergies) de chaque personne.*/

@Data
public class FireStationListPerson {

    private String firstName;

    private String lastName;

    private Integer age;

    private String phone;

    private List<String> medications;

    private List<String> allergies;

    private List<String> stationNumber;
    public FireStationListPerson(Person person, MedicalRecord medicalRecord, List<String> stationNumbers){
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phone = person.getPhone();
        this.age = medicalRecord.getAge();
        this.medications = medicalRecord.getMedications();
        this.allergies = medicalRecord.getAllergies();
        this.stationNumber = stationNumbers;
    }


    public FireStationListPerson(String firstName, String lastName, int age, String phone, List<String> medications, List<String> allergies, List<String> stationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.medications = medications;
        this.allergies = allergies;
        this.stationNumber = stationNumber;
    }
}
