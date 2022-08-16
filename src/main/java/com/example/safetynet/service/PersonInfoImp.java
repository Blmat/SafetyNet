package com.example.safetynet.service;

import com.example.safetynet.dto.PersonInfoDto;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.model.Id;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class PersonInfoImp implements PersonInfo {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FireStationRepository fireStationRepository;

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
    public List<PersonInfoDto> getPersonInformation(String firstName, String lastName) {

        return personRepository.getAllPersons()
                .filter(p -> p.getLastName().equals(lastName))
                .map(this::createPersonInfo)
                .toList();
    }

    private PersonInfoDto createPersonInfo(Person person) {
        Id id = new Id(person.getFirstName(), person.getLastName());
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findAMedicalRecordById(id);
        return new PersonInfoDto(person, medicalRecordOptional);
    }

    @Override
    public List<String> findStationByAddress(String address) {

        return fireStationRepository.findAll()
                .stream()
                .map(FireStation::getAddress)
                .filter(fireStationAddress -> fireStationAddress.equals(address))
                .toList();
    }


    public List<String> findAddressByStation(int station) {

        return fireStationRepository.findAll()
                .stream()
                .filter(fireStation -> fireStation.getStation().equals(station))
                .map(FireStation::getAddress)
                .toList();
    }
}
