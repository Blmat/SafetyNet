package com.example.safetynet.repository;

import com.example.safetynet.dto.PersonAggregate;
import com.example.safetynet.model.Id;

import java.util.Optional;
import java.util.stream.Stream;

public interface PersonAggregateRepository {

    Stream<PersonAggregate> getAllPersonAggregates();

    Optional<PersonAggregate> getPersonAggregateById(Id id);

    Stream<PersonAggregate> getAllPersonAggregatesByAddress(String address);
}
