package com.example.safetynet.controller;

import com.example.safetynet.dto.FireStationListPerson;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.FireStationCoverageImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FireStationCoveredController.class)
class FireStationCoveredControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    FireStationCoverageImp fireAlertService;

    /*----------------------------------------------------------------------------------------------------------*/
     /*-------------------------------------------GetTest------------------------------------------------------*/

    @Test
    public void getPersonsByAddress() throws Exception {

        List<FireStationListPerson> fireAlertList = new ArrayList<>();
        Person person= new Person("John","Boyd","1509 Culver St", "Culver","132456","843-795-426"," email@mail.com");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now().minusYears(35), List.of(), List.of());
        FireStationListPerson fireStationListPerson = new FireStationListPerson(person,medicalRecord, null);
        fireAlertList.add(fireStationListPerson);

        when(fireAlertService.getPersonsByAddress("1509 Culver St")).thenReturn(fireAlertList);

        mvc.perform(MockMvcRequestBuilders.get("/fire")
                        .param("address", "1509 Culver St"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getPersonsByAddressWithIncorrectParamName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fire")
                        .param("adres", "8 rue de la paix"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(400))
                .andExpect(status().reason("Required request parameter 'address' for method parameter type String is not present"));
    }
    @Test
    public void getPersonsByAddressWithIncorrectParamValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fire")
                        .param("address", " "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getPersonsCoverageByStationNumber() throws Exception {

        List<FireStationListPerson> fireAlertList = new ArrayList<>();
        Person person= new Person("John","Boyd","1509 Culver St", "Culver","132456","843-795-426"," email@mail.com");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", LocalDate.now().minusYears(35), List.of(), List.of());
        FireStationListPerson fireStationListPerson = new FireStationListPerson(person,medicalRecord, null);
        fireAlertList.add(fireStationListPerson);

        when(fireAlertService.getPersonsCoverageByStationNumber(3)).thenReturn(List.of());

        mvc.perform(MockMvcRequestBuilders.get("/firestation")
                        .param("stationNumber", "3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getPersonsCoverageByStationNumberWithIncorrectParamName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/firestation")
                        .param("statio", "3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(400))
                .andExpect(status().reason("Required request parameter 'stationNumber' for method parameter type Integer is not present"));
    }
    @Test
    public void getPersonsCoverageByStationNumberWithIncorrectParamValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/firestation")
                        .param("stationNumber", "-1"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}