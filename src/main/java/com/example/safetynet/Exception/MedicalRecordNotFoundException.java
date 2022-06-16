package com.example.safetynet.Exception;

import com.example.safetynet.model.Id;

public class MedicalRecordNotFoundException extends RuntimeException{

    public MedicalRecordNotFoundException (Id id) {
        super("Medical Record not found whit id = " + id);
    }
}
