package com.example.safetynet.repository;

import com.example.safetynet.dto.FireStation;
import com.example.safetynet.service.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FireStationRepositoryImp implements FireStationRepository {

    private final JsonReader jsonReader;

    @Autowired
    public FireStationRepositoryImp(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @Override
    public List<FireStation> findAll() {
        return this.jsonReader.getDatas().getFirestations();
    }

    @Override
    public FireStation addFireStation(FireStation firestation) {
        jsonReader.getDatas().getFirestations().add(firestation);
        return firestation;
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation, String address) {

        FireStation fireStationNew = findStationByAddress(address);
        fireStationNew.setStation(fireStation.getStation());

        return jsonReader.getDatas().getFirestations().set(jsonReader.getDatas().getFirestations().indexOf(findStationByAddress(address)), fireStationNew);
    }

    @Override
    public FireStation deleteByAddress(String address) {
        jsonReader.getDatas().getFirestations().removeIf(s -> s.getAddress().equals(address));
        return null;
    }

    @Override
    public FireStation deleteByStation(Integer station) {
        jsonReader.getDatas().getFirestations().removeIf(s -> s.getStation().equals(station));
        return null;
    }

    @Override
    public List<FireStation> findByStations(List<Integer> stations) {
        List<FireStation> list = new ArrayList<>();
        for (FireStation s : jsonReader.getDatas().getFirestations()) {
            if (stations.contains(s.getStation())) {
                list.add(s);
            }
        }
        return list;
    }

    @Override
    public FireStation findStationByAddress(String address) {
        return jsonReader.getDatas().getFirestations().stream()
                .filter(s -> (s.getAddress().equals(address))).findAny().orElseThrow();
    }
}
