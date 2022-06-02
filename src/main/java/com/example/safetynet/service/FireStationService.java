package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationService implements FireStationServiceInterface {

    @Autowired
    FireStationRepository fireStationRepository;

    private static Logger logger = LogManager.getLogger("FireStationService");

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public List<FireStation> getFireStation() {
        return fireStationRepository.findAll();
    }

    public FireStation deleteFireStationByAddress(String address) {
        fireStationRepository.deleteByAddress(address);
        return null;
    }

    public FireStation deleteFireStationByStation(Integer station) {
        fireStationRepository.deleteByStation(station);
        return null;
    }

    @Override
    public FireStation addFireStation(FireStation station) {
        return fireStationRepository.addFireStation(station);
    }

    public FireStation updateFireStation(FireStation fireStation, String address) {
        return fireStationRepository.updateFireStation(fireStation, address);
    }

}
