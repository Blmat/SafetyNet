package com.example.safetynet.service;

import com.example.safetynet.model.Person;

public interface PersonInterface {

    Person addPerson(Person person);

    Person updatePerson(Person person, String firstName, String lastName);

    Person deletePerson(String firstName, String lastName);

}
