package com.example.safetynet.service;

import com.example.safetynet.dto.Household;

import java.util.List;

public interface FloodService {

    List<Household> getHouseAttachedToFireStation(Integer stationNumber);
}
