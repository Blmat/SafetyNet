package com.example.safetynet.service;

import com.example.safetynet.dto.Flood;
import com.example.safetynet.dto.Household;
import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.dto.Person;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FloodServiceImp implements FloodService {

    private final PersonRepository personRepository;
    private final MedicalRecordInfo medicalRecordInfo;
    private final FireStationCoverage fireStationCoverageImp;


    public FloodServiceImp(PersonRepository personRepositoryImp, MedicalRecordInfo medicalRecordInfo, FireStationCoverage fireStationCoverageImp) {
        this.personRepository = personRepositoryImp;
        this.medicalRecordInfo = medicalRecordInfo;
        this.fireStationCoverageImp = fireStationCoverageImp;
    }

    // get all the persons covered by the station and regroup them by household
    @Override
    public List<Household> getHouseAttachedToFireStation(Integer stationNumber) {
        List<String> stationAddressList = fireStationCoverageImp.getFireStationAddressByStationNumber(stationNumber);
        List<Person> personList = Collections.unmodifiableList((List<Person>) personRepository.getAllPersons());
        List<Household> householdsList = new ArrayList<>();

        for(String address: stationAddressList) {
            List<Flood> floodList = new ArrayList<>();
            for(Person person: personList) {
                if (person.getAddress().equals(address)){
                    Flood flood = new Flood();
                    flood.setFirstName(person.getFirstName());
                    flood.setLastName(person.getLastName());
                    flood.setPhone(person.getPhone());
                    MedicalRecord medicalRecord = null;
                    assert false;
                    flood.setAge(medicalRecord.getAge());
                    flood.setMedications(medicalRecordInfo.getMedications(person.getFirstName(), person.getLastName()));
                    flood.setAllergies(medicalRecordInfo.getAllergies(person.getFirstName(), person.getLastName()));
                    floodList.add(flood);
                }
            }
            Household household = new Household(address, floodList);
            householdsList.add(household);
        }
        return householdsList;
    }

}
