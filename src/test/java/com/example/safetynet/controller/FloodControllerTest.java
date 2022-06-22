package com.example.safetynet.controller;

import com.example.safetynet.model.Flood;
import com.example.safetynet.model.Household;
import com.example.safetynet.service.FloodServiceImplement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    FloodServiceImplement floodServiceImplement;
    @Test
    public void getHouseholdByFireStationAddressTest() throws Exception {
        List<Household> hh = new ArrayList<>();
        List<Flood> floodList = new ArrayList<>();
        Flood flood = new Flood("eee", "eee", 15, "000", null, null);
        floodList.add(flood);
        Household household = new Household("eee", floodList);
        hh.add(household);

        when(floodServiceImplement.getHouseAttachedToFireStation("2")).thenReturn(hh);

        this.mvc.perform(MockMvcRequestBuilders.get("/flood/stations")
                        .param("stations", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[{\"address\":\"eee\",\"flood\":[{\"firstName\":\"eee\",\"lastName\":\"eee\",\"age\":15,\"phone\":\"000\",\"medications\":null,\"allergies\":null}]}]"));
    }
    @Test
    public void getHouseholdByFireStationAddressTestWithIncorrectParamName() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/flood/stations")
                        .param("a", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getHouseholdByFireStationAddressTestWithIncorrectParamValue() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/flood/stations")
                        .param("stations", "a"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }
}