package com.example.safetynet.repository;

import com.example.safetynet.dto.PersonAggregate;

import java.util.stream.Stream;

public interface PersonAggregateRepository {

    Stream<PersonAggregate> getAllPersonAggregates();

    Stream<PersonAggregate> getAllPersonAggregatesByAddress(String address);
}
