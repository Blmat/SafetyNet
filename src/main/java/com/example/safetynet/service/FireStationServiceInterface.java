package com.example.safetynet.service;

import com.example.safetynet.model.FireStation;

public interface FireStationServiceInterface {

    FireStation addFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation, String address);

    void deleteFireStationByAddress(String address);

    void deleteFireStationByStation(Integer station);
}
