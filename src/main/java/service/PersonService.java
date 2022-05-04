package service;

import model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PersonRepository;

import java.util.List;

public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Persons> getPersons() { // get list of Person
        return personRepository.findAll();
    }

    public void deletePerson(String firstName, String lastName) { // delete person by firstName and firstName
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public Persons addPerson(Persons person){ // add person to the list
        return personRepository.addPerson(person);
    }

    public Persons updatePerson(Persons personOld, String firstName, String lastName){ // update person by firstName and lastName
        return personRepository.updatePerson(personOld, firstName, lastName);
    }

    public List<Persons> findPersonByLastName(String lastName){ // find person by last name
        return personRepository.findByLastName(lastName);
    }

    public List<Persons> findEmailByCity(String city){ // find email by city
        return personRepository.findByCity(city);
    }

    public List<Persons> findByAddress(String address){ // find person by address
        return personRepository.findByAddress(address);
    }
}

