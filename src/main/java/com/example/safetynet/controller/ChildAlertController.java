package com.example.safetynet.controller;

import com.example.safetynet.model.ChildAlert;
import com.example.safetynet.service.ChildAlertServiceImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChildAlertController {

    private static final Logger logger = LogManager.getLogger(ChildAlertController.class);

    @Autowired
    ChildAlertServiceImplement childAlertService;


    /*http://localhost:8080/childAlert?address=<address>
Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.*/
    @GetMapping("/childAlert")
    public List getChildByAddress(@RequestParam String address) {
        List<ChildAlert> response = childAlertService.getChildByAddress(address);
        List<String> error = new ArrayList<>();
        logger.error("The request doesn't match with anything or is incorrect");

        // Si la liste est vide, tout est bon, c'est juste que rien ne correspond dans le fichier Json
        if(response.isEmpty()) {
            logger.error("HTTP GET request received, ERROR / Response = " + response.toString());
            return error;
        } else {
            logger.info("HTTP GET request received, SUCCESS / Response = " + response.toString());
            return response;
        }
    }
}
