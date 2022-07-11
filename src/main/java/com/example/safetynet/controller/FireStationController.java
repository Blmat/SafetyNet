package com.example.safetynet.controller;

import com.example.safetynet.dto.FireStation;
import com.example.safetynet.service.FireStationServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FireStationController {


    private final FireStationServiceInterface fireStationServiceInterface;

    public FireStationController(FireStationServiceInterface fireStationService) {
        this.fireStationServiceInterface = fireStationService;
    }


    @PostMapping(value = "/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation station) {
        log.info("Firestation created");
        return new ResponseEntity<>(fireStationServiceInterface.addFireStation(station), HttpStatus.CREATED);
    }

    @PutMapping(value = "/firestation")
    public ResponseEntity<FireStation> updateAStation(@RequestBody FireStation station, @RequestParam String address) {
        if (address.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info(address + "'s station has been updated");
        return new ResponseEntity<>(fireStationServiceInterface.updateFireStation(station, address), HttpStatus.OK);
    }

    @DeleteMapping("/firestation")
    public ResponseEntity<Void> deleteMappingStation(@RequestBody Integer station) {
        if (station < 0) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Firestation n° " + station + " has been deleted");
        fireStationServiceInterface.deleteFireStationByStation(station);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteMappingAddress(@RequestBody String address) {
        if (address.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Firestation n° " + address + " has been deleted");
        fireStationServiceInterface.deleteFireStationByStation(Integer.valueOf(address));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
