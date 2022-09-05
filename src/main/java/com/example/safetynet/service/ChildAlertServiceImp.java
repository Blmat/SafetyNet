package com.example.safetynet.service;

import com.example.safetynet.dto.ChildAlert;
import com.example.safetynet.dto.PersonAggregate;
import com.example.safetynet.exception.MedicalRecordNotFoundException;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildAlertServiceImp implements ChildAlertService {
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public ChildAlertServiceImp(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /** Trouver tous les enfants vivant à cette adresse.*/
    @Override
    public List<ChildAlert> getChildByAddress(String address) {

        List<PersonAggregate> persons = personRepository.findByAddress(address)
                .stream()
                .map(p -> new PersonAggregate(p, medicalRecordRepository.findAMedicalRecordById(p.getId())
                        .orElseThrow(()->new MedicalRecordNotFoundException("Medical Record is not found with id = " + p.getId())) ))
                .toList();

        return persons
                .stream()
                .filter(p -> p.getMedicalRecord().isMinor())
                .map(p -> new ChildAlert(
                        p.getMedicalRecord(),
                        persons
                                .stream()
                                .filter(personAggregate -> !personAggregate.getPerson().getId().equals(p.getPerson().getId()))
                                .map(PersonAggregate::getName)
                                .toList()))
                .toList();
    }
}
