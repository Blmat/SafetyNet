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

    private final FireStationCoverage fireStationCoverageImp;

    private final PersonRepository personRepositoryImp;

    public PhoneAlertServiceImp(FireStationCoverage fireStationCoverageImp, PersonRepository personRepositoryImp) {
        this.fireStationCoverageImp = fireStationCoverageImp;
        this.personRepositoryImp = personRepositoryImp;
    }

    // obtenir la liste des numéros de téléphone de toute les personnes qui sont couvert par une station de pompier spécifique.
    @Override
    public List<String> getPhoneNumberByCoverage(Integer fireStationNumber) {

        return fireStationRepository.findByStation(fireStationNumber)
                .stream()
                .map(FireStation::getAddress)
                .map(address -> personRepositoryImp.findByAddress(address).stream().map(Person::getPhone).toList())
                .flatMap(Collection::stream)
                .toList();
    }
}
