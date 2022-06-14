package com.example.safetynet.model;

import java.util.List;
import java.util.Optional;

public class PersonInfo {

    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    public PersonInfo (Optional<Person>personOptional, Optional<MedicalRecord>medicalRecordOptional) {

        Person person = personOptional.orElseThrow(()-> new IllegalArgumentException("Person is empty"));
        MedicalRecord medicalRecord = medicalRecordOptional.orElseThrow(()-> new IllegalArgumentException("Medical Record is empty"));

        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.age = medicalRecord.getAge();
        this.email = person.getEmail();
        this.medications = medicalRecord.getMedications();
        this.allergies = medicalRecord.getAllergies();
    }

    public String getFirstName() { return firstName; }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() { return email; }

    public List<String> getMedications() {
        return medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    @Override
    public String toString() {
        return "Person: firstname= " + firstName + ", lastName = " + lastName + ", address = " + address + ", age = " + age + ", email = " + email + ", medications = " + medications + ", allergies = " + allergies;
    }
}
