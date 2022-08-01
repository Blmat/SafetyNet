package com.example.safetynet.service;

import com.example.safetynet.exception.MedicalRecordNotFoundException;
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

    /* La liste doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
    elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
    moins) dans la zone desservie.*/
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
                MedicalRecord medicalRecord = null;
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
//        return fireStationRepository.findByAddress(address)
//                .stream()
//                .map(p -> createFireStationListPerson(p, stationNumbersByAddress)).toList();


    }



    private FireStationCoveragePerson createFireStationCoveragePersonList (PersonCovered personCovered,FireStationCoveragePerson fireStationCoveragePerson) {
        MedicalRecord medicalRecord= medicalRecordRepository.findAMedicalRecordById(personCovered.getId())
                .orElseThrow(()-> new MedicalRecordNotFoundException("Medical Record not found with id = " + personCovered.getId()));

        List<FireStationCoveragePerson> personListCoveredByStationNumber = getPersonsCoverageByStationNumber(fireStationCoveragePerson.getStationNumber());
        return new FireStationCoveragePerson(fireStationCoveragePerson.getAdults(), fireStationCoveragePerson.getChild(), (List<PersonCovered>) fireStationCoveragePerson);
    }

    @Override
    public List<FireStationListPerson> getPersonsByAddress(String address) {

        List<String> stationNumbersByAddress = getFireStationStationNumberByAddress(address);

        return personRepository.findByAddress(address)
                .stream()
                .map(p -> createFireStationListPerson(p, stationNumbersByAddress)).toList();
    }

    private FireStationListPerson createFireStationListPerson(Person person, List<String> stationNumbersByAddress) {
        MedicalRecord medicalRecord = medicalRecordRepository.findAMedicalRecordById(person.getId())
                .orElseThrow(() -> new MedicalRecordNotFoundException("Medical Record not found with id = " + person.getId()));
        return new FireStationListPerson(person, medicalRecord, stationNumbersByAddress);
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
}