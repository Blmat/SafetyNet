package com.example.safetynet.controller;

import com.example.safetynet.dto.PersonInfo;
import com.example.safetynet.service.PersonInfoImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {

    private static final Logger logger = LogManager.getLogger(PersonInfoController.class);

    //todo : preferer l'injection de dependance par contoller
    final PersonInfoImplement personInfoImplement;

    public PersonInfoController(PersonInfoImplement personInfoImplement) {
        this.personInfoImplement = personInfoImplement;
    }

    /* Donne toutes les infos d'une personne grâce à son nom et prénom
     * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, posologie, allergies)
     * de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent toutes apparaître.
     * */
    @GetMapping("/personInfo")
    public List getAPersonInformation(@RequestParam String firstName, String lastName) {
        List<PersonInfo> response = (List<PersonInfo>) personInfoImplement.getPersonInformation(firstName, lastName);
        List<String> error = new ArrayList<>();
        logger.error("The request doesn't match anything or is incorrect");

        // Si la liste est vide, tout est bon, c'est juste que rien ne correspond dans le fichier Json
        if (response.isEmpty()) {
            logger.error("HTTP GET request received, SUCCESS / Response = " + response.toString());
            return error;
        } else {
            logger.info("HTTP GET request received, ERROR / Response = " + response.toString());
            return response;
        }
    }
}
