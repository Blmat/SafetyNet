package model;

import lombok.Data;

import java.util.List;

@Data
public class MedicalRecords {
    private Integer id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;
}
