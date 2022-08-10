package com.example.safetynet.service;

import com.example.safetynet.dto.Flood;
import com.example.safetynet.dto.Household;
import com.example.safetynet.model.FireStation;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.PersonAggregateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloodServiceImp implements FloodService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final FireStationRepository fireStationRepository;



    public FloodServiceImp(PersonRepository personRepositoryImp, MedicalRecordRepository medicalRecordRepository, FireStationRepository fireStationRepositoryImp) {
        this.personRepository = personRepositoryImp;
        this.fireStationRepository = fireStationRepositoryImp;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    // obtenir une liste des personnes couvertes par une station de pompier et les regrouper par foyer.
    @Override
    public List<Household> getHouseAttachedToFireStation(Integer stationNumber) {

        List<FireStation> stationAddressList = fireStationRepository.findByStation(stationNumber);

        return stationAddressList
                .stream()
                .collect(Collectors.toMap(FireStation::getAddress, this::getFloods))
                .entrySet()
                .stream()
                .map(e -> new Household(e.getKey(), e.getValue()))
                .toList();

//        List<String> fireStationAddresses = stationAddressList.stream()
//                .map(FireStation::getAddress).toList();
//
//        for (String address : fireStationAddresses) {
//
//           List<Person> people= personRepository.findByAddress(address);
//            List<String> infoPerson = people.stream()
//                    .map(Person::getFirstName).toList();
//                         people.stream()
//                    .map(Person::getLastName).toList();
//            people.stream()
//                    .map(Person::getPhone).toList();
//
//
//        }
//        return householdsList;

//        List<FireStation> stationAddressList = fireStationRepository.findByStation(stationNumber);
//        List<Person> personList = personRepository.getAllPersons().toList();
//        List<Household> householdsList = new ArrayList<>();
//
//        for(FireStation fireStationAddress: stationAddressList) {
//            List<Flood> floodList = new ArrayList<>();
//            for(Person person: personList) {
//                MedicalRecord medicalRecord = medicalRecordRepository.findAMedicalRecordById(person.getId())
//                        .orElseThrow(() -> new MedicalRecordNotFoundException("Medical Record not found with id = " + person.getId()));
//                Flood flood = new Flood();
//                if (person.getAddress().equals(fireStationAddress.getAddress())){
//                    flood.setFirstName(person.getFirstName());
//                    flood.setLastName(person.getLastName());
//                    flood.setPhone(person.getPhone());
//                    flood.setAge(medicalRecord.getAge());
//                    flood.setMedications(medicalRecord.getMedications());
//                    flood.setAllergies(medicalRecord.getAllergies());
//                    floodList.add(flood);
//                }
//            }
//            Household household = new Household(personList.toString(), floodList);
//            householdsList.add(household);
//        }
//        return householdsList;
    }
        private List<Flood> getFloods(FireStation address) {
        return personAggregateRepository.getAllPersonAggregatesByAddress(address.getAddress()).map(Flood::new).toList();
    }
}
