package com.example.safetynet.controller;

import com.example.safetynet.service.CommunityEmailServiceImplement;
import com.example.safetynet.service.CommunityEmailServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommunityEmailController {
    private final CommunityEmailServiceInterface communityEmailServiceInterface;

    public CommunityEmailController(CommunityEmailServiceImplement communityEmailServiceImplement) {
        this.communityEmailServiceInterface = communityEmailServiceImplement;
    }

    /*http://localhost:8080/communityEmail?city=<city>
    Cette url doit retourner les adresses mail de tous les habitants de la ville.*/
    @GetMapping(value = "/communityEmail")
    public ResponseEntity<String> getEmailsByCity(@RequestParam String city) {
        if (city.isBlank()) {
            log.info("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("getAPersonInformation called");
        communityEmailServiceInterface.getEmailByCity(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
