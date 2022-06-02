package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;

import java.util.List;

public interface FireStationServiceInterface {

    List<FireStation> getFireStation();

    FireStation addFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation, String address);

    FireStation deleteFireStationByAddress(String address);

    FireStation deleteFireStationByStation(Integer station);
}
