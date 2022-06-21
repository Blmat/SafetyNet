package com.example.safetynet.controller;

import com.example.safetynet.service.CommunityEmailServiceImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommunityEmailController {
    private static final Logger logger = LogManager.getLogger(CommunityEmailController.class);

    @Autowired
    CommunityEmailServiceImplement communityEmailServiceImplement;

    /*http://localhost:8080/communityEmail?city=<city>
    Cette url doit retourner les adresses mail de tous les habitants de la ville..*/
    @GetMapping(value = "/communityEmail")
    public List<String> getEmailsByCity(@RequestParam String city) {
        List<String> response = communityEmailServiceImplement.getEmailByCity(city);
        List<String> error = new ArrayList<>();
        error.add("The request '" + city + "' doesn't match anything or is incorrect");

        logger.info("Request = /communityEmail?city=" + city);
        // Si la liste est vide, tout est bon, c'est juste que rien ne correspond dans le fichier Json
        if (!response.isEmpty()) {
            logger.info("HTTP GET request received, SUCCESS / Response = " + response.toString());
            return response;
        } else {
            logger.error("HTTP GET request received, ERROR / Response = " + response.toString());
            return error;
        }
    }
}
