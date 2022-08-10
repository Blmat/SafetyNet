package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommunityEmailServiceImp implements CommunityEmailService {
    private final PersonRepository personRepository;

    public CommunityEmailServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // trouver tous les e-mails des personnes vivant dans la ville rentré en paramètre.
    @Override
    public List<String> getEmailByCity(String city) {

        return personRepository.getAllPersons()
                .filter(p -> Objects.equals(p.getCity(), city))
                .map(Person::getEmail)
                .toList();
    }
}
