package com.example.safetynet.controller;

import com.example.safetynet.service.CommunityEmailService;
import com.example.safetynet.service.CommunityEmailServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CommunityEmailController {
    private final CommunityEmailService communityEmailService;

    public CommunityEmailController(CommunityEmailServiceImp communityEmailServiceImp) {
        this.communityEmailService = communityEmailServiceImp;
    }

    /*http://localhost:8080/communityEmail?city=<city>
    Cette url doit retourner les adresses mail de tous les habitants de la ville.*/
    @GetMapping(value = "/communityEmail")
    public ResponseEntity<List<String>> getEmailsByCity(@RequestParam String city) {
        log.info("getAPersonInformation called");

        return new ResponseEntity<>(communityEmailService.getEmailByCity(city), HttpStatus.OK);
    }
}
