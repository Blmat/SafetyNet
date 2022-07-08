package com.example.safetynet.service;

import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.dto.Person;
import com.example.safetynet.dto.PersonInfo;

import java.util.List;
import java.util.Set;

public interface PersonInfoInterface {

    PersonInfo getPersonInformation(String firstName, String lastName);

    public List<Person> findPersonsByStationNumber(int station);

    public List<Person> findPersonsByAddress(String address);

    public List<MedicalRecord> findMedicalRecordsByListPerson(List<Person> personneByAddress);

    public int findStationByAddress(String address);

    public Set<String> findAddressByStation(int station);

    public List<Person> findPersonsByStation(List<Integer> stations);

    public List<Person> findPersonByFistNameAndLastName(String firstName, String lastName);

    public MedicalRecord findMedicalRecordsByPerson(Person person);

    public List<String> findEmailByCity(String city);

    public List<String> findPhoneByStationNumber(int station);


}
