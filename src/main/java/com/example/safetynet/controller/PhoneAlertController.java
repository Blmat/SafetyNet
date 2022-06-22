package com.example.safetynet.controller;

import com.example.safetynet.service.PhoneAlertServiceImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PhoneAlertController {
    private static final Logger logger = LogManager.getLogger(PhoneAlertController.class);

    @Autowired
    PhoneAlertServiceImplement phoneAlertServiceImplement;

    @GetMapping("/phoneAlert")
    public List<String> getPhoneNumberByCoverage(@RequestParam String firestation) {
        List<String> response = phoneAlertServiceImplement.getPhoneNumberByCoverage(firestation);
        List<String> error = new ArrayList<>();
        logger.error("The request doesn't match anything or is incorrect");

        // If the response list is empty, it means that the request is correct but the parameter doesn't match with anything the json file
        if (response.isEmpty()) {
            logger.error("HTTP GET request received, ERROR / Response = " + response.toString());
            return error;
        } else {
            logger.info("HTTP GET request received, SUCCESS / Response =" + response.toString());
            return response;
        }
    }
}
