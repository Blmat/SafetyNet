package com.example.safetynet.service;

import com.example.safetynet.model.DataContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class JsonReaderImpl implements JsonReader{

    @Getter
    private final DataContainer datas;

    public JsonReaderImpl() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var file = new ClassPathResource("data.json").getFile();
        log.info("Data file address = " + file.getAbsolutePath());
        //read the json file stored in resources and parse it into the DataContainer model
        datas = mapper.readValue(file, DataContainer.class);
    }
}
