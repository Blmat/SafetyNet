package com.example.safetynet.repository;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

    private final DataContainer dataContainer;

    @Autowired
    public PersonRepository(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public List<Person> findAll() {
        return this.dataContainer.getPersons();
    }

    public Person addPerson(Person person) {
        dataContainer.getPersons().add(person);
        return person;
    }

    public Person updatePerson(Person person, String firstName, String lastName) throws IndexOutOfBoundsException {

        Person researchPerson = findByFirstNameAndLastName(firstName, lastName);
        researchPerson.setAddress(person.getAddress());
        researchPerson.setCity(person.getCity());
        researchPerson.setZip(person.getZip());
        researchPerson.setPhone(person.getPhone());
        researchPerson.setEmail(person.getEmail());

        return dataContainer.getPersons().set(dataContainer.getPersons().indexOf(findByFirstNameAndLastName(firstName, lastName)), researchPerson);
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        dataContainer.getPersons().removeIf(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }

    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return this.dataContainer.getPersons().stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName()
                        .equals(lastName))).findAny().orElseThrow();
    }

    public List<Person> findByLastName(String lastName){
        return this.dataContainer.getPersons().stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public List<Person> findByCity(String city){
        return this.dataContainer.getPersons().stream()
                .filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public List<Person> findByAddress(String address){
        return this.dataContainer.getPersons().stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }


}
