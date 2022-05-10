package com.example.safetynet.repository;

import com.example.safetynet.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

    private final List<Person> personList = new ArrayList<>();

    public List<Person> findAll() {
        return this.personList;
    }

    public Person addPerson(Person person) {
        personList.add(person);
        return person;
    }

    public Person updatePerson(Person person, String firstName, String lastName) throws IndexOutOfBoundsException {

        Person researchPerson = findByFirstNameAndLastName(firstName, lastName);
        researchPerson.setAddress(person.getAddress());
        researchPerson.setCity(person.getCity());
        researchPerson.setZip(person.getZip());
        researchPerson.setPhone(person.getPhone());
        researchPerson.setEmail(person.getEmail());

        return personList.set(personList.indexOf(findByFirstNameAndLastName(firstName, lastName)), researchPerson);
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        personList.removeIf(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }

    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return this.personList.stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))).findAny().orElseThrow();
    }

    public List<Person> findByLastName(String lastName){
        return this.personList.stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public List<Person> findByCity(String city){
        return this.personList.stream()
                .filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public List<Person> findByAddress(String address){
        return this.personList.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    public List<Person> findByPhone(String phone){
        return this.personList.stream()
                .filter(person -> person.getPhone().equals(phone))
                .collect(Collectors.toList());
    }

    public List<Person> findByEmail(String email){
        return this.personList.stream()
                .filter(person -> person.getEmail().equals(email))
                .collect(Collectors.toList());
    }
}
