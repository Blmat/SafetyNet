package com.example.safetynet.controller;

import com.example.safetynet.model.ChildAlert;
import com.example.safetynet.service.ChildAlertServiceImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChildAlertController {

    private static final Logger logger = LogManager.getLogger(ChildAlertController.class);

    @Autowired
    ChildAlertServiceImplement childAlertService;

    @GetMapping("/childAlert")
    public List getChildByAddress(@RequestParam String address) {
        List<ChildAlert> response = childAlertService.getChildByAddress(address);
        List<String> error = new ArrayList<>();
        logger.error("The request doesn't match with anything or is incorrect");

        // If the response list is empty, it means that the request is correct but the parameter doesn't match with anything the json file
        if(!response.isEmpty()) {
            logger.info("HTTP GET request received, SUCCESS / Response = " + response.toString());
            return response;
        } else {
            logger.error("HTTP GET request received, ERROR / Response = " + response.toString());
            return error;
        }
    }
}
