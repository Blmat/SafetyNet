package com.example.safetynet.controller;

import com.example.safetynet.model.Household;
import com.example.safetynet.service.FloodServiceImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FloodController {
    private static final Logger logger = LogManager.getLogger(FloodController.class);

    @Autowired
    FloodServiceImplement floodServiceImplement;

    @GetMapping("/flood/stations")
    public List getHouseAttachedToFireStation(@RequestParam String stations) {
        List<Household> response = floodServiceImplement.getHouseAttachedToFireStation(stations);
        List<String> error = new ArrayList<>();
        logger.error("The request doesn't match anything or is incorrect");

        // Si la liste est vide, tout est bon, c'est juste que rien ne correspond dans le fichier Json
        if (response.isEmpty()) {
            logger.error("HTTP GET request received, ERROR / Response = " + response.toString());
            return error;
        } else {
            logger.info("HTTP GET request received, SUCCESS / Response = " + response.toString());
            return response;
        }
    }
}

