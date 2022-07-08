package com.example.safetynet.controller;

import com.example.safetynet.dto.Person;
import com.example.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class PersonController {

   private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        logger.info("Person added");
        return new ResponseEntity(personService.addPerson(person), HttpStatus.CREATED);
    }

    @PutMapping(value = "/person")
    public ResponseEntity updatePerson(@RequestBody Person person, @RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("Person not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        log.info(firstName + " " + lastName + " " + "has been updated");
        return new ResponseEntity<>(personService.updatePerson(person, firstName, lastName), HttpStatus.OK);

    }

    @DeleteMapping(value = "/person")
    public ResponseEntity deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("Firstname or lastname blank");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info(firstName + " " + lastName + " " + "has been deleted");
        personService.deletePerson(firstName, lastName);
        return new ResponseEntity<>(personService.deletePerson(firstName,lastName),HttpStatus.OK);
    }
}
