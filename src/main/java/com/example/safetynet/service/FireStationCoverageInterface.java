package com.example.safetynet.service;

import com.example.safetynet.model.FireStationCoveragePerson;
import com.example.safetynet.model.FireStationListPerson;

import java.util.List;

public interface FireStationCoverageInterface {

    List<FireStationCoveragePerson> getPersonsCoverageByStationNumber(String stationNumber);

    List<String> getFireStationAddressByStationNumber(String stationNumber);

    List<String> getFireStationStationNumberByAddress(String address);

    List<FireStationListPerson> getPersonsByAddress(String address);
}
