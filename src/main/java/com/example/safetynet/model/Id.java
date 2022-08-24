package com.example.safetynet.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class Id {

    private final String firstName;
    private final String lastName;

    public Id(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
