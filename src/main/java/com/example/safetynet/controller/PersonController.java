package com.example.safetynet.controller;

import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @GetMapping(value = "/person")
    public ResponseEntity getPersons() {
        logger.info("List of person generated");
        return new ResponseEntity(personService.getPersons(), HttpStatus.OK);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        logger.info("Person created");
        return new ResponseEntity(personService.addPerson(person), HttpStatus.CREATED);
    }

    @PutMapping(value = "/person")
    public ResponseEntity updatePerson(@RequestBody Person person, @RequestParam String firstName, @RequestParam String lastName){
        if (firstName.isBlank() || lastName.isBlank() || firstName.isEmpty() || lastName.isEmpty()){
            logger.error("Person not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            logger.info(firstName + " " + lastName + " " + "has been updated");
            return new ResponseEntity(personService.updatePerson(person, firstName, lastName), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/person")
    public ResponseEntity deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank() || firstName.isEmpty() || lastName.isEmpty()) {
            logger.error("Firstname or lastname blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            logger.info(firstName + " " + lastName + " " + "has been deleted");
            personService.deletePerson(firstName, lastName);
        }
        return null;
    }

}
