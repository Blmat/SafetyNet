package com.example.safetynet.controller;

import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonInt;
import com.example.safetynet.service.PersonServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PersonController {

    private final PersonInt personIntService;

    public PersonController(PersonServiceImp personServiceImp) {
        this.personIntService = personServiceImp;
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        log.info("Person added");
        return new ResponseEntity<>(personIntService.addPerson(person), HttpStatus.CREATED);
    }

    @PutMapping(value = "/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info(firstName + " " + lastName + " " + "has been updated");
        return new ResponseEntity<>(personIntService.updatePerson(person, firstName, lastName), HttpStatus.OK);

    }

    @DeleteMapping(value = "/person")
    public ResponseEntity<Void> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info(firstName + " " + lastName + " " + "has been deleted");
        personIntService.deletePerson(firstName, lastName);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
