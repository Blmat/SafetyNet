package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonReader {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Value("src/main/resources/data.json")
    private String filePath;

    @PostConstruct
    // PostConstruct est utilisée sur une méthode qui doit être exécutée après l'injection de dépendances (ici le filePath)
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream(filePath));
        loadPersons(node);
        loadFireStations(node);
        loadMedical(node);
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

    public void loadFireStations(JsonNode node) {
        JsonNode fireStations = node.path("fireStations");

        for (JsonNode nodeFireStation : fireStations) {

            FireStation fireStation = new FireStation();
            fireStation.setAddress(nodeFireStation.path("address").asText());
            fireStation.setStation(nodeFireStation.path("station").asInt());

            fireStationRepository.addFireStation(fireStation);
        }
    }

    public void loadMedical(JsonNode node) {
        JsonNode medicalRecords = node.path("medicalRecords");

        for (JsonNode nodeMedicalRecord : medicalRecords) {

            MedicalRecord medicalRecord = new MedicalRecord();
            JsonNode allergies = nodeMedicalRecord.path("allergies");
            List<String> currentAllergies = new ArrayList<>();
            if (allergies.isArray()) {
                for (JsonNode nodeAllergies : allergies) {
                    currentAllergies.add(nodeAllergies.asText());
                }
            }
            JsonNode medications = nodeMedicalRecord.path("medications");
            List<String> currentMedications = new ArrayList<>();
            if (medications.isArray()) {
                for (JsonNode nodeMedications : medications) {
                    currentMedications.add(nodeMedications.asText());
                }
            }
            medicalRecord.setFirstName(nodeMedicalRecord.path("firstName").asText());
            medicalRecord.setLastName(nodeMedicalRecord.path("lastName").asText());
            medicalRecord.setBirthdate(nodeMedicalRecord.path("birthdate").asText());
            medicalRecord.setAllergies(currentAllergies);
            medicalRecord.setMedications(currentMedications);

            medicalRecordRepository.addMedicalRecord(medicalRecord);
        }
    }
}
