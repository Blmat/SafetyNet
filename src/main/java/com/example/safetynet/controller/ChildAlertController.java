package com.example.safetynet.controller;

import com.example.safetynet.dto.ChildAlert;
import com.example.safetynet.service.ChildAlertServiceImplement;
import com.example.safetynet.service.ChildAlertserviceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChildAlertController {
    private final ChildAlertserviceInterface childAlertserviceInterface;

    public ChildAlertController(ChildAlertServiceImplement childAlertServiceImplement) {
        this.childAlertserviceInterface = childAlertServiceImplement;
    }

    /*http://localhost:8080/childAlert?address=<address>
Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.*/
    @GetMapping("/childAlert")
    public ResponseEntity<ChildAlert> getChildByAddress(@RequestParam String address) {
        if (address.isBlank()) {
            log.info("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("getChildByAddress called");
        childAlertserviceInterface.getChildByAddress(address);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
