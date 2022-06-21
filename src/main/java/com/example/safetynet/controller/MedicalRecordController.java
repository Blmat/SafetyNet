package com.example.safetynet.controller;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping(value = "/medicalRecord")
    /*Adds a medical record*/
    public ResponseEntity <MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("MedicalRecord POST request - SUCCESS");
        return new ResponseEntity<> (medicalRecordService.addMedicalRecord(medicalRecord), HttpStatus.CREATED);
    }

    @PutMapping(value = "/medicalRecord")
    public ResponseEntity <MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, @RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            logger.error("Medical record not found");
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
            logger.info("Medical record has been updated");
            return new ResponseEntity<> (medicalRecordService.updateMedicalRecord(medicalRecord, firstName, lastName), HttpStatus.OK);
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity <Void> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            logger.error("Firstname or lastname blank");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Medical record has been deleted");
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
        }return new ResponseEntity <>(HttpStatus.OK);
    }

}