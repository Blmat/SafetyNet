package com.example.safetynet.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class DataContainer {

    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecord> medicalrecords;

    public DataContainer() {
    }

    public DataContainer(List<Person> personList, List<FireStation> firestationList, List<MedicalRecord> medicalRecordList) {
        this.persons = personList;
        this.firestations = firestationList;
        this.medicalrecords = medicalRecordList;
    }
}
