package com.example.safetynet.integrationTest;

import com.example.safetynet.dto.MedicalRecord;
import com.example.safetynet.dto.Person;
import com.example.safetynet.model.DataContainer;
import com.example.safetynet.repository.FireStationRepository;
import com.example.safetynet.repository.MedicalRecordRepository;
import com.example.safetynet.repository.PersonRepository;
import com.example.safetynet.service.PersonInfo;
import com.example.safetynet.service.PersonInfoImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public class PersonIntInfoImpUnitTest {

    @MockBean
    DataContainer dataContainer;

    private PersonRepository personRepository;

    private MedicalRecordRepository medicalRecordRepository;

    private FireStationRepository fireStationRepository;

    public PersonInfoImp personInfoImp;

    @BeforeEach
    private void init(){
        personInfoImp = new PersonInfoImp(personRepository, fireStationRepository, medicalRecordRepository);
    }


    @Test
    public void getPersonInformationTest() throws Exception {
        //given
        Person person = new Person("John", "Boyd", "1509 Culver St", null, null, null, null);
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now(), new ArrayList<>(), new ArrayList<>());

        //when
        when(personRepository.getAllPersons()).thenReturn(Stream.of(person));
        when(medicalRecordRepository.findAMedicalRecordById(Mockito.any())).thenReturn(Optional.of(medicalRecord));

        List<PersonInfo> response =  personInfoImp.getPersonInformation("John", "Boyd");

        //then
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
        Person personInfoDtoResponse = (Person) response.stream().findFirst().get();

        Assertions.assertEquals(person.getFirstName(), personInfoDtoResponse.getFirstName());
        Assertions.assertEquals(person.getLastName(), personInfoDtoResponse.getLastName());
        Assertions.assertEquals(person.getAddress(), personInfoDtoResponse.getAddress());
        Assertions.assertEquals(person.getEmail(), personInfoDtoResponse.getEmail());
        Assertions.assertEquals(medicalRecord.getAge(), medicalRecord.getAge());

    }

}
