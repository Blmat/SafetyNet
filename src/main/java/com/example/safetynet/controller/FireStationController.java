package com.example.safetynet.controller;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {

    private static final Logger logger = LogManager.getLogger(FireStationController.class);

    @Autowired
    FireStationService fireStationService;


    @PostMapping(value = "/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation station) {
        logger.info("Firestation created");
        return new ResponseEntity<>(fireStationService.addFireStation(station), HttpStatus.CREATED);
    }

    @PutMapping(value = "/firestation")
    public ResponseEntity<FireStation> updateAStation(@RequestBody FireStation station, @RequestParam String address) {
        if (address.isBlank()) {
            logger.error("Address not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info(address + "'s station has been updated");
            return new ResponseEntity<>(fireStationService.updateFireStation(station, address), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/firestation")
    public ResponseEntity deleteFireStation(@RequestParam String address, Integer station) {
        if (address.isBlank() || station == null) {
            logger.error("Address or station blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
