package com.example.safetynet.controller;

import com.example.safetynet.model.Children;
import com.example.safetynet.service.ChildrenService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChildAlertController {

    private final Logger logger;

    public ChildAlertontroller(Logger logger) {
        this.logger = logger;
    }

    @Autowired
    ChildrenService childrenService;





    @GetMapping("/children")
    public List getChildByAddress(@RequestParam String address) {
        List<Children> response = childrenService.getChildrenByAddress(address);
        List<String> error = new ArrayList<>();
        error.add("The request '" + address + "' doesn't match anything or is incorrect");

        logger.info("List of Firestations generated");
        // If the response list is empty, it means that the request is correct but the parameter doesn't match with anything the json file
        if(!response.isEmpty()) {
            logger.info("HTTP GET request received is SUCCESSFUL");
            return response;
        } else {
            logger.error("HTTP GET request received ERROR " );
            return error;
        }
    }
}
