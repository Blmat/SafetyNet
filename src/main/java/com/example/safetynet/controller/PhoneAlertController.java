package com.example.safetynet.controller;

import com.example.safetynet.service.PhoneAlertService;
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

    public PhoneAlertController(PhoneAlertService phoneAlertServiceImplement) {
        this.phoneAlertService = phoneAlertServiceImplement;
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<String> getPhoneNumberByCoverage(@RequestParam Integer firestation) {

        if (firestation<=0) {
            log.info("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("HTTP GET request received");
            phoneAlertService.getPhoneNumberByCoverage(firestation);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


//    @GetMapping("/phoneAlert")
//    public ResponseEntity<List<String>> getPhoneNumbers(@RequestParam("firestation") Integer firestationNumber) {
//        log.info("REST : Get a list of phone numbers covered by the firestation number");
//        try {
//             phoneAlertService.getPhoneNumberByCoverage(firestationNumber);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (FireNotFoundException e) {
//            log.error("REST : Get a list of phone numbers covered by the firestation number error because firestation number : {}" + " is not found", firestationNumber);
//            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

}
