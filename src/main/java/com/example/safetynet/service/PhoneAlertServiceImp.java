package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneAlertServiceImp implements PhoneAlertService {

    private final FireStationCoverage fireStationCoverageImp;

    private final PersonRepository personRepositoryImp;

    public PhoneAlertServiceImp(FireStationCoverage fireStationCoverageImp, PersonRepository personRepositoryImp) {
        this.fireStationCoverageImp = fireStationCoverageImp;
        this.personRepositoryImp = personRepositoryImp;
    }

    // get the Phone Numbers of all the Persons covered by the station who is in parameter
    @Override
    public List<String> getPhoneNumberByCoverage(Integer fireStationNumber) {

        List<Person> personList = personRepositoryImp.getAllPersons().toList();
        List<String> phoneNumberList = new ArrayList<>();

        for (Person person : personList) {

            if (fireStationCoverageImp.getFireStationAddressByStationNumber(fireStationNumber).contains(person.getAddress())) {
                phoneNumberList.add(person.getPhone());
            }
        }
        return phoneNumberList;
    }
}
