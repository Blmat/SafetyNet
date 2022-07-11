package com.example.safetynet.service;

import com.example.safetynet.dto.FireStationCoveragePerson;
import com.example.safetynet.dto.FireStationListPerson;

import java.util.List;

public interface FireStationCoverage {

    List<FireStationCoveragePerson> getPersonsCoverageByStationNumber(Integer stationNumber);

    List<String> getFireStationAddressByStationNumber(Integer stationNumber);

    List<String> getFireStationStationNumberByAddress(String address);

    List<FireStationListPerson> getPersonsByAddress(String address);
}
