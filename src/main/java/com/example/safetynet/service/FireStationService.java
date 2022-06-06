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

    @Override
    public List<FireStation> getFireStation() {
        return fireStationRepository.findAll();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void deleteFireStationByAddress(String address) {
        fireStationRepository.deleteByAddress(address);
    }

    @Override
    public void deleteFireStationByStation(Integer station) {
        fireStationRepository.deleteByStation(station);
    }

    @Override
    public FireStation addFireStation(FireStation station) {
        return fireStationRepository.addFireStation(station);
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation, String address) {
        return fireStationRepository.updateFireStation(fireStation, address);
    }

}
