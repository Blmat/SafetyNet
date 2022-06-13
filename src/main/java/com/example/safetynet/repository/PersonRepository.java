package com.example.safetynet.repository;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.Id;
import com.example.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PersonRepository {

    private final DataContainer dataContainer;

    @Autowired
    public PersonRepository(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public Stream<Person> getAllPersons() {
        return this.dataContainer.getPersons().stream();
    }

    public Optional<Person> getPersonById(Id id) {
        return getAllPersons().filter(person -> person.getId().equals(id)).findFirst();
    }


    // TODO ● ajouter une nouvelle personne ;
    public Person addPerson(Person person) {
        dataContainer.getPersons().add(person);
        return person;
    }

    //TODO ● mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de
    // famille ne changent pas, mais que les autres champs peuvent être modifiés) ;
    public Person updatePerson(Person person, Id id) throws IndexOutOfBoundsException {

        Person researchPerson = findById();
        researchPerson.setAddress(person.getAddress());
        researchPerson.setCity(person.getCity());
        researchPerson.setZip(person.getZip());
        researchPerson.setPhone(person.getPhone());
        researchPerson.setEmail(person.getEmail());

        return dataContainer.getPersons().set(dataContainer.getPersons().indexOf(findById(id)), researchPerson);
    }

    //TODO ● supprimer une personne (utilisez une combinaison de prénom et de nom
    // comme identificateur unique)
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        dataContainer.getPersons().removeIf(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }

    public Person findById( Id id  ) {
        return this.dataContainer.getPersons().stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName()
                        .equals(lastName))).findAny().orElseThrow();
    }

    public List<Person> findByLastName(String lastName){
        return this.dataContainer.getPersons().stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public List<Person> findByCity(String city){
        return this.dataContainer.getPersons().stream()
                .filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public List<Person> findByAddress(String address){
        return this.dataContainer.getPersons().stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }
}
