package com.example.safetynet.controller;

import com.example.safetynet.dto.FireStationListPerson;
import com.example.safetynet.service.FireStationCoverageImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FireStationCoveredController.class)
class FireStationCoveredControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    FireStationCoverageImp fireAlertService;

    @Test
    public void getPersonsByAddress() throws Exception {

        List<FireStationListPerson> fireAlertList = new ArrayList<>();
        FireStationListPerson fireStationListPerson = new FireStationListPerson("John", "Boyd", 38, "841-874-6512", null, null, null);
        fireAlertList.add(fireStationListPerson);

        when(fireAlertService.getPersonsByAddress("1509 Culver St")).thenReturn(fireAlertList);

        mvc.perform(MockMvcRequestBuilders.get("/fire")
                        .param("address", "1509 Culver St"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"age\":38,\"phone\":\"841-874-6512\",\"medications\":null,\"allergies\":null,\"stationNumber\":null}]"));
    }

    @Test
    public void getPersonsByAddressWithIncorrectParamValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fire")
                        .param("a", "1509 Culver St"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(400))
                .andExpect(status().reason("Required request parameter 'address' for method parameter type String is not present"));

    }

    @Test
    public void getPersonsByAddressWithIncorrectParamName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fire")
                        .param("address", "8 rue de la paix"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));


    }
}