package com.example.safetynet.service;

import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
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

    @Autowired
    private PersonInfoImplement (DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }
    private static final Logger logger = LogManager.getLogger("PersonService");


    @Override
    public List<Person> findPersonsByStationNumber(int station) {
       // Récupération des données du fichier Json via interface
        List<Person> persons = dataContainer.getPersons();
        List<FireStation> firestation = dataContainer.getFirestations();
        List<Person> personArrayList = new ArrayList<>();

        // Boucle pour récupérer l'adresse de la station puis comparer avec les adresses des
        // personnes qu'on récupère si elle sont identique
        for (FireStation fireStation : firestation) {
            logger.debug("firestation n " + firestation);
            if (fireStation.getStation() == station) {
                String address = fireStation.getAddress();
                for (Person person : persons) {
                    logger.debug("person n " + person);
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
        List<Integer> stationsList = stations;
        List<FireStation> firestations = dataContainer.getFirestations();
        List<Person> persons = dataContainer.getPersons();
        List<Person> AllPerson = new ArrayList<>();
        for (int station : stationsList) {
            for (FireStation firestation : firestations) {
                if (firestation.getStation() == station) {
                    String address = firestation.getAddress();
                    for (Person person : persons) {
                        if (person.getAddress().equalsIgnoreCase(address)) {
                            AllPerson.add(person);
                        }
                    }
                }
            }
        }
        return AllPerson;
    }

    @Override
    public List<Person> findPersonByFistNameAndLastName(String firstName, String lastName) {
        return (List<Person>) this.dataContainer.getPersons().stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName()
                        .equals(lastName))).findAny().orElseThrow();
    }

    @Override
    public MedicalRecord findMedicalRecordsByPerson(Person person) {
        MedicalRecord medicalRecordPerson = new MedicalRecord();
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
