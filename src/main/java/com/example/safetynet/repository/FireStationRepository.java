package com.example.safetynet.repository;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FireStationRepository {

    private final DataContainer dataContainer;

    @Autowired
    public FireStationRepository(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }
    public List<FireStation> findAll() {
        return this.dataContainer.getFirestations();
    }

    public FireStation addFireStation(FireStation firestation) {
       dataContainer.getFirestations().add(firestation);
        return firestation;
    }

    public FireStation updateFireStation(FireStation fireStationOld, String address) {

        FireStation fireStationNew = findStationByAddress(address);
        fireStationNew.setStation(fireStationOld.getStation());

        return dataContainer.getFirestations().set(dataContainer.getFirestations().indexOf(findStationByAddress(address)), fireStationNew);
    }

    public void deleteByAddress(String address) {
        dataContainer.getFirestations().removeIf(s -> s.getAddress().equals(address));
    }

    public void deleteByStation(Integer station) {
       dataContainer.getFirestations().removeIf(s -> s.getStation().equals(station));
    }

    public List<FireStation> findByStations(List<Integer> stations) {
        return dataContainer.getFirestations().stream()
                .filter(s -> stations.contains(s.getStation()))
                .collect(Collectors.toList());
    }

    public List<FireStation> findByStation(String address, Integer station) {
        return dataContainer.getFirestations().stream()
                .filter(s -> station.equals(s.getStation()))
                .collect(Collectors.toList());
    }

    public FireStation findStationByAddress(String address) {
        return dataContainer.getFirestations().stream()
                .filter(s -> (s.getAddress().equals(address))).findAny().orElseThrow();
    }
}
