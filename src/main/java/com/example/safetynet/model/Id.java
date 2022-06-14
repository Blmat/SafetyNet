package com.example.safetynet.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Id {

    private final String[] arguments;

    public Id(String... arguments) {
        this.arguments = arguments;
    }
}
