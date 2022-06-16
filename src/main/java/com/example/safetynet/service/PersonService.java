package com.example.safetynet.service;

import com.example.safetynet.Exception.PersonNotFoundException;
import com.example.safetynet.model.Id;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonInterface {

    @Autowired
    private PersonRepository personRepository;

    private static final Logger logger = LogManager.getLogger("PersonService");

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person addPerson(Person person) {
        logger.debug("Person to add");
        return personRepository.addPerson(person);
    }

    @Override
    public Person updatePerson(Person person, String firstName, String lastName) {

        Id id = new Id(firstName, lastName);
        Person personToUpdate = personRepository.getPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException(("Person doesnt exist")));
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());

        return personRepository.updatePerson(person,id);
    }

    @Override
    public Person deletePerson(String firstName, String lastName) {
        logger.debug("Person to delete");
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
        return null;
    }
}


