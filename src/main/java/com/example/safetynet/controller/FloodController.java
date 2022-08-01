package com.example.safetynet.controller;

import com.example.safetynet.service.FloodServiceImp;
import com.example.safetynet.service.FloodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FloodController {
    private final FloodService floodService;

    public FloodController(FloodServiceImp floodServiceImp) {
        this.floodService = floodServiceImp;
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<String> getHouseAttachedToFireStation(@RequestParam Integer stations) {
        if (stations<=0) {
            log.error("This station " + stations + " is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("getHouseAttachedToFireStation");
        floodService.getHouseAttachedToFireStation(stations);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


