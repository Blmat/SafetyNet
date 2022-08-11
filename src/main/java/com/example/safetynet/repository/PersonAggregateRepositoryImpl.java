package com.example.safetynet.repository;

import com.example.safetynet.dto.PersonAggregate;
import com.example.safetynet.exception.MedicalRecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PersonAggregateRepositoryImpl implements PersonAggregateRepository {
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public Stream<PersonAggregate> getAllPersonAggregates() {
        return personRepository.getAllPersons()
                .map(p -> new PersonAggregate(p, medicalRecordRepository.findAMedicalRecordById(p.getId()).orElseThrow(() -> new MedicalRecordNotFoundException("Medical Record is not found with id = " + p.getId()))));
    }

    @Override
    public Stream<PersonAggregate> getAllPersonAggregatesByAddress(String address) {
        return getAllPersonAggregates().filter(p -> p.getPerson().getAddress().equals(address));
    }
}
