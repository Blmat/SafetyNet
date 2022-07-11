package com.example.safetynet.service;

import com.example.safetynet.Exception.PersonNotFoundException;
import com.example.safetynet.dto.Id;
import com.example.safetynet.repository.PersonRepositoryImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PersonIntService implements PersonInt {

    private final PersonRepositoryImp personRepositoryImp;
    private static final Logger logger = LogManager.getLogger("PersonIntService");

    public PersonIntService(PersonRepositoryImp personRepositoryImp) {
        this.personRepositoryImp = personRepositoryImp;
    }

    @Override
    public com.example.safetynet.dto.Person addPerson(com.example.safetynet.dto.Person person) {
        logger.debug("PersonInt to add");
        return personRepositoryImp.addPerson(person);
    }

    @Override
    public com.example.safetynet.dto.Person updatePerson(com.example.safetynet.dto.Person person, String firstName, String lastName) {

        Id id = new Id(firstName, lastName);
        com.example.safetynet.dto.Person personToUpdate = personRepositoryImp.getPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException(("PersonInt doesnt exist")));
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());

        return personRepositoryImp.updatePerson(person,id);
    }

    @Override
    public com.example.safetynet.dto.Person deletePerson(String firstName, String lastName) {
        logger.debug("PersonInt to delete");
        personRepositoryImp.deleteByFirstNameAndLastName(firstName, lastName);
        return null;
    }
}


