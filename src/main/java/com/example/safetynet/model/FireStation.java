package com.example.safetynet.model;

public class FireStation {
    private String address;
    private Integer station;

    public FireStation() {

    }

    public FireStation(String address, int station) {
        this.address = address;
        this.station = station;
    }

    @Override
    public String toString() {
        return "Station: address= " + address + ", Station = " + station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }
}
