package com.example.safetynet.mock;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.JsonReader;
import lombok.Getter;

import java.util.ArrayList;

public class JsonReaderMock implements JsonReader {

    @Getter
    private final DataContainer datas;

    public JsonReaderMock() {
        datas = new DataContainer(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public void addPerson(Person person){
        datas.getPersons().add(person);
    }

    public void addMedicalRecord(MedicalRecord medicalRecord){
        datas.getMedicalrecords().add(medicalRecord);
    }

    public void addFireStation(FireStation fireStation){
        datas.getFirestations().add(fireStation);
    }
}
