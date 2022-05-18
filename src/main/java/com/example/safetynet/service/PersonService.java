package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private static Logger logger = LogManager.getLogger("PersonService");

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() { // get list of Person
        return personRepository.findAll();
    }

    public void deletePerson(String firstName, String lastName) {
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public Person addPerson(Person person) {
        return personRepository.addPerson(person);
    }

    public Person updatePerson(Person personAdult, String firstName, String lastName) {
        return personRepository.updatePerson(personAdult, firstName, lastName);
    }

    public List<Person> findPersonByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }
    public Person findPersonByName(String firstName,String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Person> findPersonByCity(String city) {
        return personRepository.findByCity(city);
    }

    public List<Person> findPersonByAddress(String address) {
        return personRepository.findByAddress(address);
    }

    public List<Person> findPersonByEmail(String email) {
        return personRepository.findByAddress(email);
    }

    public List<Person> findPersonByPhoneNumber(String phone) {
        return personRepository.findByAddress(phone);
    }


}
