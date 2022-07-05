package com.example.safetynet.model;

import lombok.Data;

import java.util.List;

@Data
public class Household {

    private String address;
    private List<Flood> flood;

    public Household() {
    }

    public Household(String address, List<Flood> flood) {
        this.address = address;
        this.flood = flood;
    }
}
