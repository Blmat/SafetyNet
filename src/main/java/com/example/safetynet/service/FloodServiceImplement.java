package com.example.safetynet.service;

import com.example.safetynet.model.Flood;
import com.example.safetynet.model.Household;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodServiceImplement implements FloodServiceInterface {

    private final PersonRepository personRepository;
    private final MedicalRecordInfoImplement medicalRecordService;
    private final FireStationCoverageImplement fireStationCoverageImplement;

    private MedicalRecord medicalRecord;

    public FloodServiceImplement(PersonRepository personRepository,MedicalRecordInfoImplement medicalRecordService, FireStationCoverageImplement fireStationCoverageImplement) {
        this.personRepository = personRepository;
        this.medicalRecordService = medicalRecordService;
        this.fireStationCoverageImplement = fireStationCoverageImplement;
    }

    // get all the persons covered by the station and regroup them by household
    @Override
    public List<Household> getHouseholdByStationAddress(String stationNumber) {
        List<String> stationAddressList = fireStationCoverageImplement.getFireStationAddressByStationNumber(stationNumber);
        List<Person> personList = (List<Person>) personRepository.getAllPersons();
        List<Household> householdsList = new ArrayList<>();

        for(String address: stationAddressList) {
            List<Flood> floodList = new ArrayList<>();
            for(Person person: personList) {
                if (person.getAddress().equals(address)){
                    Flood flood = new Flood();
                    flood.setFirstName(person.getFirstName());
                    flood.setLastName(person.getLastName());
                    flood.setPhone(person.getPhone());
                    flood.setAge(medicalRecord.getAge());
                    flood.setMedications(medicalRecordService.getMedications(person.getFirstName(), person.getLastName()));
                    flood.setAllergies(medicalRecordService.getAllergies(person.getFirstName(), person.getLastName()));
                    floodList.add(flood);
                }
            }
            Household household = new Household(address, floodList);
            householdsList.add(household);
        }
        return householdsList;
    }

}
