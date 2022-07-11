package com.example.safetynet.controller;

import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.service.MedicalrecordServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MedicalRecordController {

    private final MedicalrecordServiceInterface medicalrecordServiceInterface;

    public MedicalRecordController(MedicalrecordServiceInterface medicalRecordService) {
        this.medicalrecordServiceInterface = medicalRecordService;
    }

    @PostMapping(value = "/medicalRecord")
    /*Adds a medical record*/
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("MedicalRecord POST request - SUCCESS");
        medicalrecordServiceInterface.addMedicalRecord(medicalRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/medicalRecord")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, @RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Medical record has been updated");
        return new ResponseEntity<>(medicalrecordServiceInterface.updateMedicalRecord(medicalRecord, firstName, lastName), HttpStatus.OK);
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("input error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Medical record has been deleted");
        medicalrecordServiceInterface.deleteMedicalRecord(firstName, lastName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}