package model;

import lombok.Data;

@Data
public class Persons {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private int phone;
    private String email;

}
