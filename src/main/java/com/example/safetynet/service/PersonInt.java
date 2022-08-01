package com.example.safetynet.service;

import com.example.safetynet.model.Person;

public interface PersonInt {

    Person addPerson(Person person);

    Person updatePerson(Person person, String firstName, String lastName);

    void deletePerson(String firstName, String lastName);

}
