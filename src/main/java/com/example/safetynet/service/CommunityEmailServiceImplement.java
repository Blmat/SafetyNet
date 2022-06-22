package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityEmailServiceImplement implements CommunityEmailServiceInterface {
    private final PersonRepository personRepository;

    public CommunityEmailServiceImplement(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // get all the emails of the persons living in the city passed into parameter
    @Override
    public List<String> getEmailByCity(String city) {
        List<Person> personList = (List<Person>) personRepository.getAllPersons();
        List<String> emailList = new ArrayList<>();

        for(Person person: personList) {
            if (person.getCity().equals(city)) {
                emailList.add(person.getEmail());
            }
        }
        return emailList;
    }
}
