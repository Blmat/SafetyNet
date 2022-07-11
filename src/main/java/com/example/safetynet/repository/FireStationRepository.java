package com.example.safetynet.repository;

import com.example.safetynet.dto.FireStation;

import java.util.List;

public interface FireStationRepository {

    List<FireStation> findAll();

    FireStation addFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation, String address);

    FireStation deleteByAddress(String address);

    FireStation deleteByStation(Integer station);

    List<FireStation> findByStations(List<Integer> stations);

    FireStation findStationByAddress(String address);


}
