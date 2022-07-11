package com.example.safetynet.service;

import com.example.safetynet.dto.FireStation;
import com.example.safetynet.repository.FireStationRepositoryImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FireStationServiceImp implements FireStationService {


    private final FireStationRepositoryImp fireStationRepositoryImp;
    private static final Logger logger = LogManager.getLogger("FireStationServiceImp");
    public FireStationServiceImp(FireStationRepositoryImp fireStationRepositoryImp) {
        this.fireStationRepositoryImp = fireStationRepositoryImp;
    }

    @Override
    public FireStation addFireStation(FireStation station) {
        logger.debug("FireStation added");
        return fireStationRepositoryImp.addFireStation(station);
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation, String address) {
        return fireStationRepositoryImp.updateFireStation(fireStation, address);
    }
    @Override
    public void deleteFireStationByAddress(String address) {
        logger.debug("FireStation to delete");
        fireStationRepositoryImp.deleteByAddress(address);
    }

    @Override
    public void deleteFireStationByStation(Integer station) {
        logger.debug("FireStation to delete");
        fireStationRepositoryImp.deleteByStation(station);
    }

}
