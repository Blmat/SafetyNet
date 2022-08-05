package com.example.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class Household {

    private String address;
    private List<Flood> flood;

    public Household() {
    }
    public Household(List<Flood> flood) {
        this.address = address;
    }
    public Household(String address, List<Flood> flood) {
        this.address = address;
        this.flood = flood;
    }
}
