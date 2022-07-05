package com.example.safetynet.model;

import lombok.Data;

@Data
public class FireStation {
    private String address;
    private Integer station;

    public FireStation() {

    }

    public FireStation(String address, int station) {
        this.address = address;
        this.station = station;
    }
}
