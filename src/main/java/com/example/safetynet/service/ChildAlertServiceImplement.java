package com.example.safetynet.service;

import com.example.safetynet.dto.ChildAlert;
import com.example.safetynet.dto.PersonAggregate;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildAlertServiceImplement implements ChildAlertserviceInterface {
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public ChildAlertServiceImplement( PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository =  medicalRecordRepository;
    }

    // get all the child living at the address in parameter
    @Override
    public List<ChildAlert> getChildByAddress(String address) {

        List<PersonAggregate> persons = personRepository.getAllPersons()
                .filter(p -> p.getAddress().equals(address))
                .map(p -> new PersonAggregate(p, medicalRecordRepository.findAMedicalRecordById(p.getId()).orElseThrow()))
                .toList();

        return persons
                .stream()
                .filter(p -> p.getMedicalRecord().isMinor())
                .map(p -> new ChildAlert(
                        p.getPerson(),
                        p.getMedicalRecord(),
                        persons
                                .stream()
                                .filter(personAggregate -> !personAggregate.getPerson().getId().equals(p.getPerson().getId()))
                                .map(person-> p.getPerson().getFirstName()+" "+p.getPerson().getLastName())
                                .toList()))
                .toList();
    }
}
