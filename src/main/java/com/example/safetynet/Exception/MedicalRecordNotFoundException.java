package com.example.safetynet.Exception;

import com.example.safetynet.model.Id;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalRecordNotFoundException extends RuntimeException{

    public MedicalRecordNotFoundException (Id id) {
        super("Medical Record not found whit id = " + id);
    }
}
