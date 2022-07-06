package com.example.safetynet.controller;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.service.FireStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class FireStationController {


    private FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }


    @PostMapping(value = "/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation station) {
        log.info("Firestation created");
        return new ResponseEntity<>(fireStationService.addFireStation(station), HttpStatus.CREATED);
    }

    @PutMapping(value = "/firestation")
    public ResponseEntity<FireStation> updateAStation(@RequestBody FireStation station, @RequestParam String address) {
        if (address.isBlank()) {
            log.error("Address not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info(address + "'s station has been updated");
            return new ResponseEntity<>(fireStationService.updateFireStation(station, address), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/firestation")
    public ResponseEntity deleteFireStation(@RequestParam String address, Integer station) {
        if (address.isBlank() || station == null) {
            log.error("Address or station blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
