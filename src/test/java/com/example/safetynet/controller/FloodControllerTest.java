package com.example.safetynet.controller;

import com.example.safetynet.dto.Flood;
import com.example.safetynet.dto.Household;
import com.example.safetynet.service.FloodServiceImp;
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

@WebMvcTest(FloodController.class)
class FloodControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    FloodServiceImp floodServiceImp;
    @Test
    public void getHouseholdByFireStationAddressTest() throws Exception {
        List<Household> hh = new ArrayList<>();
        List<Flood> floodList = new ArrayList<>();
        Flood flood = new Flood("eee", "eee", 15, "000", null, null);
        floodList.add(flood);
        Household household = new Household("eee", floodList);
        hh.add(household);

        when(floodServiceImp.getHouseAttachedToFireStation("2")).thenReturn(hh);

        mvc.perform(MockMvcRequestBuilders.get("/flood/stations")
                        .param("stations", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"address\":\"eee\",\"flood\":[{\"firstName\":\"eee\",\"lastName\":\"eee\",\"age\":15,\"phone\":\"000\",\"medications\":null,\"allergies\":null}]}]"));
    }
    @Test
    public void getHouseholdByFireStationAddressTestWithIncorrectParamName() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/flood/stations")
                        .param("a", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().reason("Required request parameter 'stations' for method parameter type String is not present"));
    }
    @Test
    public void getHouseholdByFireStationAddressTestWithEmptyParamValue() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/flood/stations")
                        .param("stations", ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }
}