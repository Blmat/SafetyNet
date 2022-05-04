package controller;


import model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

@RestController
public class PersonController {

    private final static Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @GetMapping(value = "/person")
    public ResponseEntity<Persons> getPersons() {
        logger.info("List of person generated");
        return new ResponseEntity(personService.getPersons(), HttpStatus.OK);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Persons> addPerson(@RequestBody Persons person) {
        logger.info("Person created");
        return new ResponseEntity(personService.addPerson(person), HttpStatus.CREATED);
    }

    @PutMapping(value = "/person")
    public ResponseEntity<Persons> updatePerson(@RequestBody Persons person, @RequestParam String firstName, @RequestParam String lastName){
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
