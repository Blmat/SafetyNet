package com.example.safetynet.service;

import com.example.safetynet.dto.Flood;
import com.example.safetynet.dto.Household;
import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.dto.Person;
import com.example.safetynet.repository.PersonRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodServiceImp implements FloodService {

    private final PersonRepositoryImp personRepositoryImp;
    private final MedicalRecordInfoImp medicalRecordService;
    private final FireStationCoverageImp fireStationCoverageImp;

    private MedicalRecord medicalRecord;

    public FloodServiceImp(PersonRepositoryImp personRepositoryImp, MedicalRecordInfoImp medicalRecordService, FireStationCoverageImp fireStationCoverageImp) {
        this.personRepositoryImp = personRepositoryImp;
        this.medicalRecordService = medicalRecordService;
        this.fireStationCoverageImp = fireStationCoverageImp;
    }

    // get all the persons covered by the station and regroup them by household
    @Override
    public List<Household> getHouseAttachedToFireStation(String stationNumber) {
        List<String> stationAddressList = fireStationCoverageImp.getFireStationAddressByStationNumber(Integer.valueOf(stationNumber));
        List<Person> personList = (List<Person>) personRepositoryImp.getAllPersons();
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
