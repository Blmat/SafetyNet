package com.example.safetynet.controller;

import com.example.safetynet.dto.ChildAlert;
import com.example.safetynet.service.ChildAlertService;
import com.example.safetynet.service.ChildAlertServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ChildAlertController {
    private final ChildAlertService childAlertservice;

    public ChildAlertController(ChildAlertServiceImp childAlertServiceImp) {
        this.childAlertservice = childAlertServiceImp;
    }

    /**http://localhost:8080/childAlert?address=<address>
Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.*/
    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildAlert>> getChildByAddress(@RequestParam String address) {
        log.info("getChildByAddress called");
      var response=  childAlertservice.getChildByAddress(address);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
