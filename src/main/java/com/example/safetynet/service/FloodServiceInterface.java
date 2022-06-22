package com.example.safetynet.service;

import com.example.safetynet.model.Household;

import java.util.List;

public interface FloodServiceInterface {

    List<Household> getHouseAttachedToFireStation(String stationNumber);
}
