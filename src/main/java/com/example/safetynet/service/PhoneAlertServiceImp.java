package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PhoneAlertServiceImp implements PhoneAlertService {

    private final PersonRepository personRepositoryImp;
    private final FireStationRepository fireStationRepository;

    public PhoneAlertServiceImp(FireStationRepository fireStationRepository, PersonRepository personRepositoryImp) {
        this.personRepositoryImp = personRepositoryImp;
        this.fireStationRepository = fireStationRepository;
    }

    /** obtenir la liste des numéros de téléphone de toutes les personnes qui sont couvertes par une station de pompier spécifique.*/
    @Override
    public List<String> getPhoneNumberByCoverage(Integer fireStationNumber) {

        return fireStationRepository.findByStation(fireStationNumber)
                .stream()
                .map(FireStation::getAddress)
                .map(address -> personRepositoryImp.findByAddress(address).stream().map(Person::getPhone).toList())
                .flatMap(Collection::stream)
                .distinct()
                .toList();
    }
}
