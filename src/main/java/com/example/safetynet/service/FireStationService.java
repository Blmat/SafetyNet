package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FireStationService implements FireStationServiceInterface {


    private final FireStationRepository fireStationRepository;

    private static final Logger logger = LogManager.getLogger("FireStationService");

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    @Override
    public FireStation addFireStation(FireStation station) {
        logger.debug("FireStation added");
        return fireStationRepository.addFireStation(station);
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation, String address) {
        return fireStationRepository.updateFireStation(fireStation, address);
    }
    @Override
    public void deleteFireStationByAddress(String address) {
        logger.debug("FireStation to delete");
        fireStationRepository.deleteByAddress(address);
    }

    @Override
    public void deleteFireStationByStation(Integer station) {
        logger.debug("FireStation to delete");
        fireStationRepository.deleteByStation(station);
    }

}
