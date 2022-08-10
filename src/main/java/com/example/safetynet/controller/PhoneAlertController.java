package com.example.safetynet.controller;

import com.example.safetynet.service.PhoneAlertService;
import com.example.safetynet.service.PhoneAlertServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PhoneAlertController {

    private final PhoneAlertService phoneAlertService;

    public PhoneAlertController(PhoneAlertServiceImp phoneAlertServiceImp) {
        this.phoneAlertService = phoneAlertServiceImp;
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<String> getPhoneNumberByCoverage(@RequestParam Integer firestation) {

        if (firestation <= 0) {
            log.info("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("HTTP GET request received");
            phoneAlertService.getPhoneNumberByCoverage(firestation);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
