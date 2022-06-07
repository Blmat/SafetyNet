package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements PersonInterface {

    @Autowired
    private PersonRepository personRepository;

    private static final Logger logger = LogManager.getLogger("PersonService");

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPersons() {
        logger.debug("Persons list to find");
        return personRepository.findAll();
    }

    @Override
    public Person addPerson(Person person) {
        logger.debug("Person to add");
        return personRepository.addPerson(person);
    }

    @Override
    public Person updatePerson(Person personMajor, String firstName, String lastName) {
        return personRepository.updatePerson(personMajor, firstName, lastName);
    }

    @Override
    public Person deletePerson(String firstName, String lastName) {
        logger.debug("Person to delete");
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
        return null;
    }
}


