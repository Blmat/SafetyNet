package com.example.safetynet.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Id {

    private final String firstName;
    private final String lastName;

    public Id(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
