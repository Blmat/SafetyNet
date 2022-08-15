package com.example.safetynet.controller;

import com.example.safetynet.dto.Household;
import com.example.safetynet.service.FloodService;
import com.example.safetynet.service.FloodServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class FloodController {
    private final FloodService floodService;

    public FloodController(FloodServiceImp floodServiceImp) {
        this.floodService = floodServiceImp;
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<List<Household>> getHouseAttachedToFireStation(@RequestParam Integer stations) {

        log.info("getToFireStation");
       final var response= floodService.getHouseToFireStation(stations);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


