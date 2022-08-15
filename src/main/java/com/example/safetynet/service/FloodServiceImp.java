package com.example.safetynet.service;

import com.example.safetynet.dto.Flood;
import com.example.safetynet.dto.Household;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.PersonAggregateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloodServiceImp implements FloodService {

    private final PersonAggregateRepository personAggregateRepository;
    private final FireStationRepository fireStationRepository;



    public FloodServiceImp(PersonAggregateRepository personAggregateRepository, FireStationRepository fireStationRepositoryImp) {
        this.personAggregateRepository = personAggregateRepository;
        this.fireStationRepository = fireStationRepositoryImp;
    }

    /** obtenir une liste des personnes couvertes par une station de pompier et les regrouper par foyer.*/
    @Override
    public List<Household> getHouseToFireStation(Integer stationNumber) {
        List<FireStation> stationNumberList = fireStationRepository.findByStation(stationNumber);

        return stationNumberList
                .stream()
                .collect(Collectors.toMap(FireStation::getAddress, this::getFloods))
                .entrySet()
                .stream()
                .map(e -> new Household(e.getKey(), e.getValue()))
                .toList();
    }
        private List<Flood> getFloods(FireStation address) {
        return personAggregateRepository.getAllPersonAggregatesByAddress(address.getAddress()).map(Flood::new).toList();
    }
}
