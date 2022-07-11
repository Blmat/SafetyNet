package com.example.safetynet.controller;

import com.example.safetynet.service.FireStationCoverage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FireStationCoveredController {
    private final FireStationCoverage fireAlertService;

    public FireStationCoveredController(FireStationCoverage fireAlertService) {
        this.fireAlertService = fireAlertService;
    }

    /*
        http://localhost:8080/fire?address=<address>
        Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
        de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
        médicaux (médicaments, posologie et allergies) de chaque personne
    * */
    @GetMapping("/fire")
    public ResponseEntity<?> getPersonsByAddress(@RequestParam String address) {
        if (address.isBlank()) {
            log.info("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("getPersonsByAddress called");
            fireAlertService.getPersonsByAddress(address);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
