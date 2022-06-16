package com.example.safetynet.model;

import java.util.List;

public class Household {

    private String address;
    private List<Flood> flood;

    public Household() { }

    public Household(String address, List<Flood> flood) {
        this.address = address;
        this.flood = flood;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Flood> getFlood() { return flood; }
    public void setFlood(List<Flood> flood) {
        this.flood = flood;
    }

    @Override
    public String toString() {
        return "address= " + address + ", person = " + flood;
    }
}
