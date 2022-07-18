package com.example.safetynet.service;

import com.example.safetynet.dto.*;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class FireStationCoverageImp implements FireStationCoverage {

    private final MedicalRecord medicalRecord = new MedicalRecord();
    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;


    public FireStationCoverageImp(PersonRepository personRepositoryImp, FireStationRepository fireStationRepositoryImp) {
        this.personRepository = personRepositoryImp;
        this.fireStationRepository = fireStationRepositoryImp;
    }

    @Override
    public List<FireStationCoveragePerson> getPersonsCoverageByStationNumber(Integer stationNumber) {

        List<Person> personList = (List<Person>) personRepository.getAllPersons();
        List<PersonCovered> personCoveredList = new ArrayList<>();
        List<FireStationCoveragePerson> stationCoverageList = new ArrayList<>();
        int adult = 0;
        int child = 0;

        for (Person person : personList) {
            if (getFireStationAddressByStationNumber(stationNumber).contains(person.getAddress())) {
                PersonCovered personCovered = new PersonCovered();
                personCovered.setFirstName(person.getFirstName());
                personCovered.setLastName(person.getLastName());
                personCovered.setAddress(person.getAddress());
                personCovered.setPhone(person.getPhone());
                personCoveredList.add(personCovered);
                if (medicalRecord.getAge() <= 18) {
                    child++;
                } else {
                    adult++;
                }
            }
        }
        if (!personCoveredList.isEmpty()) {
            FireStationCoveragePerson stationCoverage = new FireStationCoveragePerson(adult, child, personCoveredList);
            stationCoverageList.add(stationCoverage);
        }
        return stationCoverageList;
    }

    // find a station address by using it number
    @Override
    public List<String> getFireStationAddressByStationNumber(Integer stationNumber) {

        return fireStationRepository.findAll()
                .stream()
                .filter(fireStation -> fireStation.getStation().equals(stationNumber))
                .map(FireStation::getAddress)
                .toList();
    }

    // find a station number by using it address
    @Override
    public List<String> getFireStationStationNumberByAddress(String address) {

        return fireStationRepository.findAll()
                .stream()
                .map(FireStation::getAddress)
                .filter(fireStationAddress -> fireStationAddress.equals(address))
                .toList();
    }

    @Override
    public List<FireStationListPerson> getPersonsByAddress(String address) {

        List<FireStationListPerson> fireAlertList = new ArrayList<>();
        List<Person> personList = personRepository.getAllPersons().toList();

        for (Person person : personList) {
            if (person.getAddress().equals(address)) {
                FireStationListPerson fireAlert = new FireStationListPerson();
                fireAlert.setFirstName(person.getFirstName());
                fireAlert.setLastName(person.getLastName());
                fireAlert.setPhone(person.getPhone());
                fireAlert.setAge(medicalRecord.getAge());
                fireAlert.setMedications(medicalRecord.getMedications());
                fireAlert.setAllergies(medicalRecord.getAllergies());
                fireAlert.setStationNumber(getFireStationStationNumberByAddress(address));
                fireAlertList.add(fireAlert);
            }
        }
        return fireAlertList;
    }
}
