package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneAlertServiceImplement implements PhoneAlertServiceInterface {

     private final  FireStationRepository fireStationRepository;

    private final FireStationCoverageImplement fireStationCoverageImplement;

    private final PersonRepository personRepository;

    private static final Logger logger = LogManager.getLogger("FireStationService");


    public PhoneAlertServiceImplement(FireStationRepository fireStationRepository, FireStationCoverageImplement fireStationCoverageImplement, PersonRepository personRepository) {
        this.fireStationRepository = fireStationRepository;
        this.fireStationCoverageImplement = fireStationCoverageImplement;
        this.personRepository = personRepository;
    }

    // get the Phone Numbers of all the Persons covered by the station who is in parameter
    @Override
    public List<String> getPhoneNumberByCoverage(String firestation_number) {
        List<Person> personList = (List<Person>) personRepository.getAllPersons();
        List<String> phoneNumberList = new ArrayList<>();

        for(Person person : personList) {
            if(fireStationCoverageImplement.getFireStationAddressByStationNumber(firestation_number).contains(person.getAddress())) {
                phoneNumberList.add(person.getPhone());
            }
        }
        return phoneNumberList;
    }
}
