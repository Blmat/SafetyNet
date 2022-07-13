package com.example.safetynet.service;

import com.example.safetynet.Exception.PersonNotFoundException;
import com.example.safetynet.dto.*;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class PersonInfoImp implements PersonInfo {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FireStationRepository fireStationRepository;


    @Autowired
    public PersonInfoImp(PersonRepository personRepository,
                         FireStationRepository fireStationRepository,
                         MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /* Donne toutes les infos d'une personne grâce à son nom et prénom
     * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, posologie, allergies)
     * de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent toutes apparaître.
     * */
    @Override
    public List<PersonInfo> getPersonInformation(String firstName, String lastName) {

        return personRepository.getAllPersons()
                .filter(p -> p.getLastName().equals(lastName))
                .map(this::createPersonInfo)
                .toList();
    }

    private PersonInfo createPersonInfo(Person person) {
        Id id = new Id(person.getFirstName(), person.getLastName());
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findAMedicalRecordById(id);
        return (PersonInfo) new PersonInfoDto(person, medicalRecordOptional);
    }

    @Override
    public List<Person> findPersonsByStationNumber(int station) {
        // Récupération des données du fichier Json via interface
        List<Person> persons;
        persons = (List<Person>) personRepository.getAllPersons();
        List<FireStation> firestation = fireStationRepository.findAll();
        List<Person> personArrayList = new ArrayList<>();

        // Boucle pour récupérer l'adresse de la station puis comparer avec les adresses des
        // personnes qu'on récupère si elles sont identiquent
        for (FireStation fireStation : firestation) {
            log.info("The requested station has been found");
            if (fireStation.getStation() == station) {
                String address = fireStation.getAddress();
                for (Person person : persons) {
                    log.info("The requested person has been found ");
                    if (person.getAddress().equalsIgnoreCase(address)) {
                        personArrayList.add(person);
                    }
                }
            }
        }
        return personArrayList;
    }

    @Override
    public List<Person> findPersonsByAddress(String address) {
        //Todo faut il changer la liste en String ?
//        return personRepository.findByAddress(address)
//                .stream()
//                .filter(person -> person.getAddress().equals(address))
//                .map(Person::getAddress)
//                .toList();

        List<Person> persons = personRepository.findByAddress(address);
        List<Person> resultPersonByAddress = new ArrayList<>();
        for (Person person : persons) {
            if (person.getAddress().equalsIgnoreCase(address)) {
                resultPersonByAddress.add(person);
            }
        }
        return resultPersonByAddress;
    }

    @Override
    public List<MedicalRecord> findMedicalRecordsByListPerson(List<Person> personneByAddress) {

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<MedicalRecord> resultat = new ArrayList<>();
        for (MedicalRecord medicalRecord : medicalRecords) {
            for (Person person : personneByAddress) {
                if (medicalRecord.getFirstName().equalsIgnoreCase(person.getFirstName())
                        && medicalRecord.getLastName().equalsIgnoreCase(person.getLastName())) {
                    resultat.add(medicalRecord);
                }
            }
        }
        return resultat;
    }

    @Override
    public List<String> findStationByAddress(String address) {

        return fireStationRepository.findAll()
                .stream()
                .filter(fireStation -> fireStation.getAddress().equals(address))
                .map(FireStation::getAddress)
                .toList();
    }

    @Override
    public List<String> findAddressByStation(int station) {

        return fireStationRepository.findAll()
                .stream()
                .filter(fireStation -> fireStation.getStation().equals(station))
                .map(FireStation::getAddress)
                .toList();
    }

    @Override
    public List<Person> findPersonsByStation(List<Integer> stations) {
        List<FireStation> firestations = fireStationRepository.findAll();
        List<Person> persons = (List<Person>) personRepository.getAllPersons();
        List<Person> personList = new ArrayList<>();
        for (int station : stations) {
            for (FireStation firestation : firestations) {
                if (firestation.getStation() == station) {
                    String address = firestation.getAddress();
                    for (Person person : persons) {
                        if (person.getAddress().equalsIgnoreCase(address)) {
                            personList.add(person);
                        }
                    }
                }
            }
        }
        return personList;
    }

    @Override
    public List<Person> findPersonByFistNameAndLastName(String firstName, String lastName) {
        return (List<Person>) this.personRepository.getAllPersons().findFirst()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName()
                        .equals(lastName))).stream().findAny().orElseThrow(() -> new PersonNotFoundException("Sorry this person does not exist"));
    }

    @Override
    public MedicalRecord findMedicalRecordsByPerson(Person person) {
        new MedicalRecord();
        MedicalRecord medicalRecordPerson;
        List<MedicalRecord> medicalRecords = (List<MedicalRecord>) medicalRecordRepository.getAllMedicalRecord();

        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equalsIgnoreCase(person.getFirstName())
                    && medicalRecord.getLastName().equalsIgnoreCase(person.getLastName())) {
                medicalRecordPerson = medicalRecord;
                return medicalRecordPerson;
            }
        }
        return null;
    }

    @Override
    public List<String> findEmailByCity(String city) {

        return personRepository.findByCity(city)
                .stream()
                .filter(person -> person.getCity().equals(city))
                .map(Person::getEmail)
                .toList();
    }

    @Override
    public List<String> findPhoneByStationNumber(int station) {

        List<Person> persons = (List<Person>) personRepository.getAllPersons();
        List<FireStation> firestations = fireStationRepository.findAll();
        List<String> phones = new ArrayList<>();
        for (FireStation firestation : firestations) {
            if (firestation.getStation() == station) {
                String address = firestation.getAddress();
                for (Person person : persons) {
                    if (person.getAddress().equalsIgnoreCase(address)) {
                        String phone = person.getPhone();
                        phones.add(phone);
                    }
                }
            }
        }
        return phones;
    }
}
