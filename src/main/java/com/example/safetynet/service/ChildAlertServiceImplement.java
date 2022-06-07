package com.example.safetynet.service;

import com.example.safetynet.model.ChildAlert;
import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildAlertServiceImplement implements ChildAlertserviceInterface {


  private final DataContainer dataContainer;


    private final MedicalRecordRepository medicalRecordRepository;

    public ChildAlertServiceImplement(DataContainer dataContainer, MedicalRecordRepository medicalRecordRepository) {
        this.dataContainer = dataContainer;
        this.medicalRecordRepository =  medicalRecordRepository;
    }

    // get all the child living at the address in parameter
    @Override
    public List<ChildAlert> getChildByAddress(String address) {
        List<Person> personList = dataContainer.getPersons();
        List<String> family = new ArrayList<>();
        List<ChildAlert> childAlertList = new ArrayList<>();


        for(Person person: personList) {
            if(person.getAddress().equals(address)) {
                int age = medicalRecordRepository.getAge(person.getFirstName(), person.getLastName());
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
