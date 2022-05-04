package repository;

import lombok.extern.slf4j.Slf4j;
import model.Persons;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PersonRepository {



    private final List<Persons> personList = new ArrayList<>();

    public List<Persons> findAll() {
        return this.personList;
    }

    public Persons addPerson(Persons person) {
        personList.add(person);
        return person;
    }

    public Persons updatePerson(Persons personOld, String firstName, String lastName) throws IndexOutOfBoundsException {

        Persons person = (Persons) findByFirstNameAndLastName(firstName, lastName);
        person.setAddress(personOld.getAddress());
        person.setCity(personOld.getCity());
        person.setZip(personOld.getZip());
        person.setPhone(personOld.getPhone());
        person.setEmail(personOld.getEmail());

        return personList.set(personList.indexOf(findByFirstNameAndLastName(firstName, lastName)), person);
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.personList.removeIf(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }

    public Object findByFirstNameAndLastName(String firstName, String lastName) {
        return this.personList.stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))).findAny().orElseThrow();
    }

    public List<Persons> findByLastName(String lastName){
        return this.personList.stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public List<Persons> findByCity(String city){
        return this.personList.stream()
                .filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public List<Persons> findByAddress(String address){
        return this.personList.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }



}
