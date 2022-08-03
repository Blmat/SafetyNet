package com.example.safetynet.repository;

import com.example.safetynet.dto.PersonAggregate;
import com.example.safetynet.model.Id;

import java.util.Optional;
import java.util.stream.Stream;

public interface PersonAggregateRepository {
    Optional<PersonAggregate> getById(Id id);
    Stream<PersonAggregate> findAllByAddress(String address );
    Stream<PersonAggregate> getAll();
}
