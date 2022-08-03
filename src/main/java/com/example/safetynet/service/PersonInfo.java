package com.example.safetynet.service;

import com.example.safetynet.dto.PersonInfoDto;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;

import java.util.List;

public interface PersonInfo {

    List<PersonInfoDto> getPersonInformation(String firstName, String lastName);

    public List<Person> findPersonsByStationNumber(int station);

    public List<Person> findPersonsByAddress(String address);

    public List<MedicalRecord> findMedicalRecordsByListPerson(List<Person> personneByAddress);

    public List<String> findStationByAddress(String address);

    public List<String> findAddressByStation(int station);

    public List<Person> findPersonsByStation(List<Integer> stations);

    public List<Person> findPersonByFistNameAndLastName(String firstName, String lastName);

    public MedicalRecord findMedicalRecordsByPerson(Person person);


}
