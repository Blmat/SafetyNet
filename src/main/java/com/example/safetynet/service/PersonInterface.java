package com.example.safetynet.service;

import com.example.safetynet.model.Person;

import java.util.List;

public interface PersonInterface {

    List<Person> getPersons();

    Person addPerson(Person person);

    Person updatePerson(Person personMajor, String firstName, String lastName);

    Person deletePerson(String firstName, String lastName);

    List<String>  getMedications(String firstName, String lastName);

    List<String>  getAllergies(String firstName, String lastName);

    List<Person> findPersonByLastName(String lastName);

    List<Person> findEmailByCity(String city);

    List<Person> findByAddress(String address);
}
