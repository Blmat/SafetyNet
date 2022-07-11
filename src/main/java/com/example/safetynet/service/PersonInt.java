package com.example.safetynet.service;

public interface PersonInt {

    com.example.safetynet.dto.Person addPerson(com.example.safetynet.dto.Person person);

    com.example.safetynet.dto.Person updatePerson(com.example.safetynet.dto.Person person, String firstName, String lastName);

    com.example.safetynet.dto.Person deletePerson(String firstName, String lastName);

}
