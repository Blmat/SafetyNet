package com.example.safetynet.service;

import com.example.safetynet.dto.FireStation;
import com.example.safetynet.repository.FireStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FireStationServiceImp implements FireStationService {


    private final FireStationRepository fireStationRepository;

    public FireStationServiceImp(FireStationRepository fireStationRepositoryImp) {
        this.fireStationRepository = fireStationRepositoryImp;
    }

    @Override
    public FireStation addFireStation(FireStation station) {
        log.info("FireStation added");
        return fireStationRepository.addFireStation(station);
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation, String address) {
        log.info("FireStation to update");
        return fireStationRepository.updateFireStation(fireStation, address);
    }
    @Override
    public void deleteFireStationByAddress(String address) {
        log.info("FireStation to delete");
        fireStationRepository.deleteByAddress(address);
    }

    @Override
    public void deleteFireStationByStation(Integer station) {
        log.info("FireStation to delete");
        fireStationRepository.deleteByStation(station);
    }

}
