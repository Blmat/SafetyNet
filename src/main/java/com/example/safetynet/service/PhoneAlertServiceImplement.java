package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneAlertServiceImplement implements PhoneAlertServiceInterface {

    private final FireStationCoverageImplement fireStationCoverageImplement;

    private final PersonRepository personRepository;

    public PhoneAlertServiceImplement(FireStationCoverageImplement fireStationCoverageImplement, PersonRepository personRepository) {
        this.fireStationCoverageImplement = fireStationCoverageImplement;
        this.personRepository = personRepository;
    }

    // get the Phone Numbers of all the Persons covered by the station who is in parameter
    @Override
    public List<String> getPhoneNumberByCoverage(String fireStationNumber) {
        List<Person> personList = (List<Person>) personRepository.getAllPersons();
        List<String> phoneNumberList = new ArrayList<>();

        for (Person person : personList) {
            if (fireStationCoverageImplement.getFireStationAddressByStationNumber(fireStationNumber).contains(person.getAddress())) {
                phoneNumberList.add(person.getPhone());
            }
        }
        return phoneNumberList;
    }
}
