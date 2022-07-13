package com.example.safetynet.service;

import com.example.safetynet.dto.Person;

public interface PersonInt {

    Person addPerson(com.example.safetynet.dto.Person person);

    Person updatePerson(com.example.safetynet.dto.Person person, String firstName, String lastName);

    void deletePerson(String firstName, String lastName);

}
