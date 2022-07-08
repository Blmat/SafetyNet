package com.example.safetynet.service;

import com.example.safetynet.dto.Household;

import java.util.List;

public interface FloodServiceInterface {

    List<Household> getHouseAttachedToFireStation(String stationNumber);
}
