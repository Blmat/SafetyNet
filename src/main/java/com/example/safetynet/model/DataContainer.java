package com.example.safetynet.model;

import com.example.safetynet.dto.FireStation;
import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.dto.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataContainer {

    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecord> medicalrecords;

}
