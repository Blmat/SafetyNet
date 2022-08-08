package com.example.safetynet.dto;

import com.example.safetynet.model.Id;
import lombok.Data;

@Data
public class PersonCovered {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    private int adult;

    private int child;

    public Id getId() {
        return new Id(firstName, lastName);
    }

    public PersonCovered() {
    }

    public PersonCovered(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }
}
