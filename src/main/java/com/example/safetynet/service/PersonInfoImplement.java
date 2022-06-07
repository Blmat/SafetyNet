package com.example.safetynet.service;

import com.example.safetynet.model.*;
import com.example.safetynet.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonInfoImplement implements PersonInfoInterface {

    private final DataContainer dataContainer;

    private final MedicalRecordRepository medicalRecordRepository;


    @Autowired
    private PersonInfoImplement (DataContainer dataContainer, MedicalRecordRepository medicalRecordRepository) {
        this.dataContainer = dataContainer;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /* Donne toutes les infos d'une personne grâce à son nom et prénom*/
    @Override
    public List<PersonInfo> getPersonInformation(String firstName, String lastName) {
        List<Person> personList = dataContainer.getPersons();
        List<PersonInfo> personInfoList = new ArrayList<>();

        for(Person person : personList) {
            if(person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                PersonInfo personInfo = new PersonInfo();
                personInfo.setFirstName(person.getFirstName());
                personInfo.setLastName(person.getLastName());
                personInfo.setAddress(person.getAddress());
                personInfo.setEmail(person.getEmail());
                personInfo.setAge(medicalRecordRepository.getAge(person.getFirstName(), person.getLastName()));
                personInfo.setMedications(medicalRecordRepository.getMedications(person.getFirstName(), person.getLastName()));
                personInfo.setAllergies(medicalRecordRepository.getAllergies(person.getFirstName(), person.getLastName()));
                personInfoList.add(personInfo);
            }
        }
        return personInfoList;
    }


    private static final Logger logger = LogManager.getLogger("PersonService");


    @Override
    public List<Person> findPersonsByStationNumber(int station) {
       // Récupération des données du fichier Json via interface
        List<Person> persons = dataContainer.getPersons();
        List<FireStation> firestation = dataContainer.getFirestations();
        List<Person> personArrayList = new ArrayList<>();

        // Boucle pour récupérer l'adresse de la station puis comparer avec les adresses des
        // personnes qu'on récupère si elles sont identiquent
        for (FireStation fireStation : firestation) {
            logger.debug("The requested station has been found");
            if (fireStation.getStation() == station) {
                String address = fireStation.getAddress();
                for (Person person : persons) {
                    logger.debug("The requested person has been found ");
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
        List<Person> persons = dataContainer.getPersons();
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
        List<MedicalRecord> medicalRecords = dataContainer.getMedicalrecords();
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
    public int findStationByAddress(String address) {
        List<FireStation> firestations = dataContainer.getFirestations();
        int station = 0;
        for (FireStation firestation : firestations) {
            if (firestation.getAddress().equalsIgnoreCase(address)) {
                station = firestation.getStation();
            }
        }
        return station;
    }

    @Override
    public Set<String> findAddressByStation(int station) {
        List<FireStation> firestations = dataContainer.getFirestations();
        Set<String> address = new HashSet<>();
        for (FireStation firestation : firestations) {
            if (firestation.getStation() == (station)) {
                address.add(firestation.getAddress());
            }
        }
        return address;
    }

    @Override
    public List<Person> findPersonsByStation(List<Integer> stations) {
        List<FireStation> firestations = dataContainer.getFirestations();
        List<Person> persons = dataContainer.getPersons();
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
        return (List<Person>) this.dataContainer.getPersons().stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName()
                        .equals(lastName))).findAny().orElseThrow();
    }

    @Override
    public MedicalRecord findMedicalRecordsByPerson(Person person) {
        new MedicalRecord();
        MedicalRecord medicalRecordPerson;
        List<MedicalRecord> medicalRecords = dataContainer.getMedicalrecords();

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
        List<Person> persons = dataContainer.getPersons();
        List<String> emails = new ArrayList<>();
        for (Person person : persons) {
            if (person.getCity().equalsIgnoreCase(city)) {
                emails.add(person.getEmail());
            }
        }
        return emails;
    }

    @Override
    public List<String> findPhoneByStationNumber(int station) {
        List<Person> persons = dataContainer.getPersons();
        List<FireStation> firestations = dataContainer.getFirestations();
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