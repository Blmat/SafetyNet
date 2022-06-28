package com.example.safetynet.service;

import com.example.safetynet.model.*;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationCoverageImplement implements FireStationCoverageInterface{

    private MedicalRecord medicalRecord;
    @Autowired
    MedicalRecordService medicalRecordService;
    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;

    public FireStationCoverageImplement(PersonRepository personRepository, FireStationRepository fireStationRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
    }

    @Override
    public List<FireStationCoveragePerson> getPersonsCoverageByStationNumber(String stationNumber) {
        List<Person> personList = (List<Person>) personRepository.getAllPersons();
        List<PersonCovered> personCoveredList = new ArrayList<>();
        List<FireStationCoveragePerson> stationCoverageList = new ArrayList<>();
        int adult = 0;
        int child = 0;

        for( Person person : personList) {
            if(getFireStationAddressByStationNumber(stationNumber).contains(person.getAddress())) {
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
    public List<String> getFireStationAddressByStationNumber(String stationNumber) {
        List<FireStation> firestationList = fireStationRepository.findAll();
        List<String> fireStationAddressList = new ArrayList<>();

        for (FireStation fs : firestationList) {
            if (fs.getStation().equals(stationNumber)) {
                String address = fs.getAddress();
                fireStationAddressList.add(address);
            }
        }
        return fireStationAddressList;
    }

    // find a station number by using it address
    @Override
    public List<String> getFireStationStationNumberByAddress(String address) {
        List<FireStation> firestationList = fireStationRepository.findAll();
        List<String> fireStationAddressList = new ArrayList<>();

        for (FireStation fs : firestationList) {
            if (fs.getAddress().equals(address)) {
                int station = fs.getStation();
                fireStationAddressList.add(String.valueOf(station));
            }
        }
        return fireStationAddressList;
    }
    @Override
    public List<FireStationListPerson> getPersonsByAddress(String address) {
        List<FireStationListPerson> fireAlertList = new ArrayList<>();
        List<Person> personList = (List<Person>) personRepository.getAllPersons();

        for(Person person : personList) {
            if(person.getAddress().equals(address)) {
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
