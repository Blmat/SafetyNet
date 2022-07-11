package com.example.safetynet.controller;

import com.example.safetynet.dto.PersonInfoDto;
import com.example.safetynet.service.PersonInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInfoController {
    final PersonInfo personInfo;

    public PersonInfoController(PersonInfo personInfoImplement) {
        this.personInfo = personInfoImplement;
    }

    /* Donne toutes les infos d'une personne grâce à son nom et prénom
     * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, posologie, allergies)
     * de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent toutes apparaître.
     * */
    @GetMapping("/personInfo")
    public ResponseEntity<PersonInfoDto> getAPersonInformation(@RequestParam String firstName, String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.info("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("getAPersonInformation called");
        personInfo.getPersonInformation(firstName, lastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
