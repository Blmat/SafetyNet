package com.example.safetynet.controller;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class MedicalRecordController {

  private MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }
    @PostMapping(value = "/medicalRecord")
    /*Adds a medical record*/
    public ResponseEntity <MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("MedicalRecord POST request - SUCCESS");
        return new ResponseEntity<> (medicalRecordService.addMedicalRecord(medicalRecord), HttpStatus.CREATED);
    }

    @PutMapping(value = "/medicalRecord")
    public ResponseEntity <MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, @RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("Medical record not found");
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
            log.info("Medical record has been updated");
            return new ResponseEntity<> (medicalRecordService.updateMedicalRecord(medicalRecord, firstName, lastName), HttpStatus.OK);
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity <Void> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("Firstname or lastname blank");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("Medical record has been deleted");
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
        }return new ResponseEntity <>(HttpStatus.OK);
    }

}