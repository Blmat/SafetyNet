package com.example.safetynet.service;

import com.example.safetynet.model.ChildAlert;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildAlertServiceImplement implements ChildAlertserviceInterface {

    private final MedicalRecord medicalRecord;

    private final PersonRepository personRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    public ChildAlertServiceImplement( MedicalRecord medicalRecord, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecord = medicalRecord;
        this.personRepository = personRepository;
        this.medicalRecordRepository =  medicalRecordRepository;
    }

    // get all the child living at the address in parameter
    @Override
    public List<ChildAlert> getChildByAddress(String address) {
        List<Person> personList = (List<Person>) personRepository.getAllPersons();
        List<String> family = new ArrayList<>();
        List<ChildAlert> childAlertList = new ArrayList<>();


        for(Person person: personList) {
            if(person.getAddress().equals(address)) {
                int age = medicalRecord.getAge();
                if(age <= 18) {
                    ChildAlert childAlert = new ChildAlert();
                    childAlert.setFirstName(person.getFirstName());
                    childAlert.setLastName(person.getLastName());
                    childAlert.setAge(age);
                    childAlert.setFamily(family);
                    childAlertList.add(childAlert);
                } else {
                    String link = person.getFirstName() + " " + person.getLastName();
                    family.add(link);
                }
            }
        }
        return childAlertList;
    }
}
