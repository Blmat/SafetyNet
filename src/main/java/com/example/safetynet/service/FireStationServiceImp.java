package com.example.safetynet.service;

import com.example.safetynet.exception.FireNotFoundException;
import com.example.safetynet.model.FireStation;
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
        FireStation fireStationToUpdate= fireStationRepository.getByAddress(address)
                .orElseThrow(() -> new FireNotFoundException("The FireStation is not found"));
        fireStationToUpdate.setAddress(fireStation.getAddress());
        log.info("FireStation to update");

        return fireStationRepository.updateFireStation(fireStation, address);
    }
    @Override
    public void deleteFireStationByAddress(String address) {
        log.info("FireStation to delete");
        fireStationRepository.deleteByAddress(address);
    }
}
