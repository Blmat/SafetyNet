package com.example.safetynet.controller;

import com.example.safetynet.service.FireStationCoverage;
import com.example.safetynet.service.FireStationCoverageImp;
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

    public FireStationCoveredController(FireStationCoverageImp fireAlertServiceImp) {
        this.fireAlertService = fireAlertServiceImp;
    }
    /**
        http://localhost:8080/fire?address=<address>
        Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
        de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
        médicaux (médicaments, posologie et allergies) de chaque personne.
    * */
    @GetMapping("/fire")
    public ResponseEntity<?> getPersonsByAddress(@RequestParam String address) {
        if (address.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("getPersonsByAddress called");
         final var response=   fireAlertService.getPersonsByAddress(address);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /** http://localhost:8080/firestation?stationNumber=<station_number>
     Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
     Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
     doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
     elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
     moins) dans la zone desservie.*/

    @GetMapping("/firestation")
    public ResponseEntity<?> getPersonsCoverageByStationNumber(@RequestParam Integer stationNumber) {
        if (stationNumber <= 0) {
            log.error("the station number can't be 0 or <0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            log.info("getPersonsCoverageByStationNumber called");}
        final var response=   fireAlertService. getPersonsCoverageByStationNumber(stationNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
