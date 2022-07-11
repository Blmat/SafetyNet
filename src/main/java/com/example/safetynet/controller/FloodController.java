package com.example.safetynet.controller;

import com.example.safetynet.service.FloodServiceImplement;
import com.example.safetynet.service.FloodServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FloodController {
    private final FloodServiceInterface floodServiceInterface;

    public FloodController(FloodServiceImplement floodServiceImplement) {
        this.floodServiceInterface = floodServiceImplement;
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<String> getHouseAttachedToFireStation(@RequestParam String stations) {
        if (stations.isBlank()) {
            log.info("This station " + stations + " is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("getHouseAttachedToFireStation");
        floodServiceInterface.getHouseAttachedToFireStation(stations);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


