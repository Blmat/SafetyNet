package com.example.safetynet.repository;

import com.example.safetynet.dto.FireStation;

import java.util.List;
import java.util.Optional;

public interface FireStationRepository {

    Optional<FireStation> getByAddress(String address);
    List<FireStation> findAll();

    FireStation addFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation, String address);

    FireStation deleteByAddress(String address);

    FireStation deleteByStation(Integer station);

    List<FireStation> findByStations(List<Integer> stations);

    FireStation findStationByAddress(String address);


}
