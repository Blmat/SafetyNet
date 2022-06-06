package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;

import java.util.List;

public interface FireStationServiceInterface {

    List<FireStation> getFireStation();

    FireStation addFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation, String address);

    void deleteFireStationByAddress(String address);

    void deleteFireStationByStation(Integer station);
}
