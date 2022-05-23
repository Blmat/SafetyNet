package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.repository.FireStationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationService {

    @Autowired
    FireStationRepository fireStationRepository;

    private static Logger logger = LogManager.getLogger("FireStationService");

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public List<FireStation> getFireStation() {
        return fireStationRepository.findAll();
    }

    public void deleteFireStationByAddress(String address) {
        fireStationRepository.deleteByAddress(address);
    }

    public void deleteFireStationByStation(Integer station) {
        fireStationRepository.deleteByStation(station);
    }

    public FireStation addFireStation(FireStation station){
        return fireStationRepository.addFireStation(station);
    }

    public FireStation updateFireStation(FireStation fireStationOld, String address){
        return fireStationRepository.updateFireStation(fireStationOld, address);
    }

    public List<FireStation> findFireStationByStations(List<Integer> stations){
        return fireStationRepository.findByStations(stations);
    }

    public List<FireStation> findFireStationByAddressAndStation(String address ,Integer station){
        return fireStationRepository.findByStation(address,station);
    }

    public FireStation findFireStationByAddressList(String address, Integer station){
        return fireStationRepository.findStationByAddress(address);
    }
}
