package com.example.safetynet.controller;

import com.example.safetynet.service.PhoneAlertServiceImplement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
public class PhoneAlertController {

   private PhoneAlertServiceImplement phoneAlertServiceImplement;

    public PhoneAlertController(PhoneAlertServiceImplement phoneAlertServiceImplement) {
        this.phoneAlertServiceImplement = phoneAlertServiceImplement;
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneNumberByCoverage(@RequestParam String firestation) {
        List<String> response = phoneAlertServiceImplement.getPhoneNumberByCoverage(firestation);
        List<String> error = new ArrayList<>();
        log.error("The request doesn't match anything or is incorrect");

        // If the response list is empty, it means that the request is correct but the parameter doesn't match with anything the json file
        if (response.isEmpty()) {
            log.error("HTTP GET request received, ERROR / Response = " + response.toString());
            return error;
        } else {
            log.info("HTTP GET request received, SUCCESS / Response =" + response.toString());
            return response;
        }
    }
}
