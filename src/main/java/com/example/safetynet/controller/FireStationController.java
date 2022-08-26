package com.example.safetynet.controller;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.service.FireStationService;
import com.example.safetynet.service.FireStationServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FireStationController {

    private final FireStationService fireStationService;

    public FireStationController(FireStationServiceImp fireStationServiceimp) {
        this.fireStationService = fireStationServiceimp;
    }


    @PostMapping(value = "/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation station) {
        log.info("Firestation created");
        return new ResponseEntity<>(fireStationService.addFireStation(station), HttpStatus.CREATED);
    }

    @PutMapping(value = "/firestation")
    public ResponseEntity<FireStation> updateAStation(@RequestBody FireStation station, @RequestParam String address) {
        if (address.isBlank()) {
            log.error("input error (FireStation or address) ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info(address + "'s station has been updated");
        return new ResponseEntity<>(fireStationService.updateFireStation(station, address), HttpStatus.OK);
    }

    @DeleteMapping(value = "/firestation")
    public ResponseEntity<Void> deleteStationByAddress(@RequestParam String address) {
        if (address.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Firestation n° " + address + " has been deleted");
        fireStationService.deleteFireStationByAddress(address);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
