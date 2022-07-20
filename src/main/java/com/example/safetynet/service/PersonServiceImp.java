package com.example.safetynet.service;

import com.example.safetynet.Exception.PersonNotFoundException;
import com.example.safetynet.dto.Id;
import com.example.safetynet.dto.Person;
import com.example.safetynet.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonServiceImp implements PersonInt {

    private final PersonRepository personRepositoryImp;

    public PersonServiceImp(PersonRepository personRepositoryImp) {
        this.personRepositoryImp = personRepositoryImp;
    }

    @Override
    public com.example.safetynet.dto.Person addPerson(Person person) {
        log.info("PersonInt to add");
        return personRepositoryImp.addPerson(person);
    }

    @Override
    public Person updatePerson(Person person, String firstName, String lastName) {

        Id id = new Id(firstName, lastName);
        Person personToUpdate = personRepositoryImp.getPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException(("PersonInt doesnt exist")));
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());

        return personRepositoryImp.updatePerson(person, id);
    }

    @Override
    public void deletePerson(String firstName, String lastName) {
        log.info("PersonInt to delete");
        personRepositoryImp.deleteByFirstNameAndLastName(firstName, lastName);

    }
}


