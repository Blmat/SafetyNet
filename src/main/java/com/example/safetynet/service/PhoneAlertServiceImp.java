package com.example.safetynet.service;

import com.example.safetynet.dto.Person;
import com.example.safetynet.repository.PersonRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneAlertServiceImp implements PhoneAlertService {

    private final FireStationCoverageImp fireStationCoverageImp;

    private final PersonRepositoryImp personRepositoryImp;

    public PhoneAlertServiceImp(FireStationCoverageImp fireStationCoverageImp, PersonRepositoryImp personRepositoryImp) {
        this.fireStationCoverageImp = fireStationCoverageImp;
        this.personRepositoryImp = personRepositoryImp;
    }

    // get the Phone Numbers of all the Persons covered by the station who is in parameter
    @Override
    public List<String> getPhoneNumberByCoverage(String fireStationNumber) {





        List<Person> personList = personRepositoryImp.getAllPersons().toList();
        List<String> phoneNumberList = new ArrayList<>();

        for (Person person : personList) {
            if (fireStationCoverageImp.getFireStationAddressByStationNumber(Integer.valueOf(fireStationNumber)).contains(person.getAddress())) {
                phoneNumberList.add(person.getPhone());
            }
        }
        return phoneNumberList;
    }
}
