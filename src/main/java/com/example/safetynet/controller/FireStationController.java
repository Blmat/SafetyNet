package com.example.safetynet.controller;

import com.example.safetynet.dto.FireStation;
import com.example.safetynet.service.FireStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FireStationController {

    private final FireStationService fireStationService;

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
            log.error("input error (FireStation or address ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info(address + "'s station has been updated");
        return new ResponseEntity<>(fireStationService.updateFireStation(station, address), HttpStatus.OK);
    }

    @DeleteMapping("/firestation")
    public ResponseEntity<Void> deleteMappingStation(@RequestParam Integer station) {
        if (station < 0) {
            log.error("input error (station number)");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Firestation n° " + station + " has been deleted");
        fireStationService.deleteFireStationByStation(station);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteMappingAddress(@RequestParam String address) {
        if (address.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Firestation n° " + address + " has been deleted");
        fireStationService.deleteFireStationByStation(Integer.valueOf(address));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
