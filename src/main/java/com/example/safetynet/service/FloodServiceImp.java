package com.example.safetynet.service;

import com.example.safetynet.dto.Flood;
import com.example.safetynet.dto.Household;
import com.example.safetynet.exception.MedicalRecordNotFoundException;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodServiceImp implements FloodService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FireStationRepository fireStationRepository;



    public FloodServiceImp(PersonRepository personRepositoryImp, MedicalRecordRepository medicalRecordRepository, FireStationRepository fireStationRepositoryImp) {
        this.personRepository = personRepositoryImp;
        this.fireStationRepository = fireStationRepositoryImp;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    // get all the persons covered by the station and regroup them by household
    @Override
    public List<Household> getHouseAttachedToFireStation(Integer stationNumber) {

        List<FireStation> stationAddressList = fireStationRepository.findByStation(stationNumber);
        List<Person> personList = personRepository.getAllPersons().toList();
        List<Household> householdsList = new ArrayList<>();

        for(FireStation fireStationAddress: stationAddressList) {
            List<Flood> floodList = new ArrayList<>();
            for(Person person: personList) {
                MedicalRecord medicalRecord = medicalRecordRepository.findAMedicalRecordById(person.getId())
                        .orElseThrow(() -> new MedicalRecordNotFoundException("Medical Record not found with id = " + person.getId()));                    Flood flood = new Flood();
                if (person.getAddress().equals(fireStationAddress.getAddress())){
                    flood.setFirstName(person.getFirstName());
                    flood.setLastName(person.getLastName());
                    flood.setPhone(person.getPhone());
                    flood.setAge(medicalRecord.getAge());
                    flood.setMedications(medicalRecord.getMedications());
                    flood.setAllergies(medicalRecord.getAllergies());
                    floodList.add(flood);
                }
            }
            Household household = new Household(personList.toString(), floodList);
            householdsList.add(household);
        }
        return householdsList;
    }
}
