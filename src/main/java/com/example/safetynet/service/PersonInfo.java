package com.example.safetynet.service;

import com.example.safetynet.dto.PersonInfoDto;

import java.util.List;

public interface PersonInfo {

    List<PersonInfoDto> getPersonInformation(String firstName, String lastName);

     List<String> findStationByAddress(String address);

     List<String> findAddressByStation(int station);

}
