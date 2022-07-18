package com.example.safetynet.service;

import com.example.safetynet.dto.Person;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class CommunityEmailServiceImp implements CommunityEmailService {
    private final PersonRepository personRepository;

    public CommunityEmailServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // get all the emails of the persons living in the city passed into parameter
    @Override
    public List<String> getEmailByCity(String city) {

        return personRepository.getAllPersons()
                .filter(p -> Objects.equals(p.getCity(), city))
                .map(Person::getEmail)
                .collect(toList());
    }
}
