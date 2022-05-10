package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class JsonReader {

    @Autowired
    private PersonRepository personRepository;

    @Value("src/main/resources/data.json")
    private String filePath;

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream(filePath));
        loadPersons(node);
    }

    public void loadPersons(JsonNode node) {
        JsonNode persons = node.path("persons");

        for (JsonNode nodePerson : persons) {

            Person person = new Person();
            person.setFirstName(nodePerson.path("firstName").asText());
            person.setLastName(nodePerson.path("lastName").asText());
            person.setAddress(nodePerson.path("address").asText());
            person.setCity(nodePerson.path("city").asText());
            person.setZip(nodePerson.path("zip").asText());
            person.setPhone(nodePerson.path("phone").asText());
            person.setEmail(nodePerson.path("email").asText());

            personRepository.addPerson(person);
        }
    }
}
