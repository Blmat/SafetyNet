package com.example.safetynet.repository;

import com.example.safetynet.model.Id;
import com.example.safetynet.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface PersonRepository {

    Stream<Person> getAllPersons();

    Optional<Person> getPersonById(Id id);

    Person addPerson(Person person);

    Person updatePerson(Person person, Id id);

    Person deleteByFirstNameAndLastName(String firstName, String lastName);

    Person findById(Id id);

    List<Person> findByAddress(String address);
}
