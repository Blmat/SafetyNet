package com.example.safetynet.repository;

import com.example.safetynet.exception.PersonNotFoundException;
import com.example.safetynet.model.Id;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class PersonRepositoryImp implements PersonRepository {

    private final JsonReader jsonReader;

    @Autowired
    public PersonRepositoryImp(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @Override
    public Stream<Person> getAllPersons() {
        return this.jsonReader.getDatas().getPersons().stream();
    }

    @Override
    public Optional<Person> getPersonById(Id id) {
        return getAllPersons().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public Person addPerson(Person person) {
        jsonReader.getDatas().getPersons().add(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person, Id id) {

        Person researchPerson = findById(id);
        researchPerson.setAddress(person.getAddress());
        researchPerson.setCity(person.getCity());
        researchPerson.setZip(person.getZip());
        researchPerson.setPhone(person.getPhone());
        researchPerson.setEmail(person.getEmail());

        return jsonReader.getDatas().getPersons().set(jsonReader.getDatas().getPersons().indexOf(findById(id)), researchPerson);
    }

    @Override
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        jsonReader.getDatas().getPersons().removeIf(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }

    @Override
    public Person findById(Id id) {
        return this.jsonReader.getDatas().getPersons().stream()
                .filter(person -> (person.getId().equals(id))).findAny().orElseThrow(() -> new PersonNotFoundException("Input error"));
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> list = new ArrayList<>();
        for (Person person : this.jsonReader.getDatas().getPersons()) {
            if (person.getAddress().equals(address)) {
                list.add(person);
            }
        }
        return list;
    }
}
