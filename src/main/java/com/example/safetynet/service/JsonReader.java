package com.example.safetynet.service;

import com.example.safetynet.model.DataContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class JsonReader {

    public DataContainer readJSON() {

        DataContainer obj = new DataContainer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            //read the json file stored in resources and parse it into the DataContainer model
            obj = mapper.readValue(new File("./src/main/resources/data.json"), DataContainer.class);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
