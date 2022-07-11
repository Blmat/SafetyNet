package com.example.safetynet.controller;

import com.example.safetynet.service.PhoneAlertServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PhoneAlertController {

    private final PhoneAlertServiceInterface phoneAlertServiceInterface;

    public PhoneAlertController(PhoneAlertServiceInterface phoneAlertServiceImplement) {
        this.phoneAlertServiceInterface = phoneAlertServiceImplement;
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<String> getPhoneNumberByCoverage(@RequestParam String firestation) {

        if (firestation.isBlank()) {
            log.info("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("HTTP GET request received");
            phoneAlertServiceInterface.getPhoneNumberByCoverage(firestation);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
