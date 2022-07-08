package com.example.safetynet.controller;

import com.example.safetynet.dto.FireStationListPerson;
import com.example.safetynet.service.FireStationCoverageImplement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
public class FireStationCoveredController {
   private FireStationCoverageImplement fireAlertService;

    public FireStationCoveredController(FireStationCoverageImplement fireAlertService) {
        this.fireAlertService = fireAlertService;
    }

    @GetMapping("/fire")
    public List getPersonsByAddress(@RequestParam String address) {
        List<FireStationListPerson> response = fireAlertService.getPersonsByAddress(address);
        List<String> error = new ArrayList<>();
        log.error("The request doesn't match anything or is incorrect");

        // Si la liste est vide, tout est bon, c'est juste que rien ne correspond dans le fichier Json
        if (response.isEmpty()) {
            log.error("HTTP GET request received, ERROR / Response = " + response.toString());
            return error;
        } else {
            log.info("HTTP GET request received, SUCCESS / Response = " + response.toString());
            return response;
        }
    }
}
