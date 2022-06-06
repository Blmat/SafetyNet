package com.example.safetynet.service;

import com.example.safetynet.model.Children;
import com.example.safetynet.model.DataContainer;
import com.example.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildrenService implements ChildrenServiceInterface {

    @Autowired
    DataContainer dataContainer;

    @Autowired
    MedicalRecordService medicalRecordService;

    public ChildrenService(DataContainer dataContainer, MedicalRecordService medicalRecordService) {
        this.dataContainer = dataContainer;
        this.medicalRecordService = medicalRecordService;
    }

    /* Donne une liste de tous les enfants vivant à l'adresse voulu*/
    @Override
    public List<Children> getChildrenByAddress(String address) {
        List<Person> personList = dataContainer.getPersons();
        List<String> family = new ArrayList<>();
        List<Children> childAlertList = new ArrayList<>();


        for(Person person: personList) {
            if(person.getAddress().equals(address)) {
                int age = medicalRecordService.getAge(person.getFirstName(), person.getLastName());
                if(age < 18) {
                    Children children = new Children();
                    children.setFirstName(person.getFirstName());
                    children.setLastName(person.getLastName());
                    children.setAge(age);
                    children.setFamily(family);
                    childAlertList.add(children);
                } else {
                    String attach = person.getFirstName() + " " + person.getLastName();
                    family.add(attach);
                }
            }
        }
        return childAlertList;
    }
}
