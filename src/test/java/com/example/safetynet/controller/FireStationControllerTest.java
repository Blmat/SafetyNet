package com.example.safetynet.controller;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.service.FireStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FireStationControllerTest {

    @Autowired
    private FireStationController fireStationController;

    @MockBean
    private FireStationService fireStationService;

    private FireStation fireStation;

    @BeforeEach
    private void init() {
        fireStation = new FireStation("1509 Culver St", 3);
    }

    /*----------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------GetTest------------------------------------------------------*/
    @Test
    void getFireStationTest() throws Exception {
        when(fireStationService.getFireStation()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/firestation");
        MockMvcBuilders.standaloneSetup(fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /*----------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------AddTest------------------------------------------------------*/
    @Test
    void addFireStationTest() throws Exception {
        when(this.fireStationService.addFireStation(any(FireStation.class))).thenReturn(fireStation);

        String content = (new ObjectMapper()).writeValueAsString(fireStation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"address\":\"1509 Culver St\",\"station\":3}"));
    }

    /*-----------------------------------------------------------------------------------------------------------*/
    /*-----------------------------------------UpdateTest-----------------------------------------------------*/
    @Test
    void updateFireStationTest() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("8 rue de la paix");
        fireStation.setStation(1);

        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);

        String content = (new ObjectMapper()).writeValueAsString(fireStation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/firestation")
                .param("address", "8 rue de la paix")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"address\":\"8 rue de la paix\",\"station\":1}"));
    }

    @Test
    void updateFireStationAddressEmptyTest() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("\"8 rue de la paix");
        fireStation.setStation(1);

        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);

        String content = (new ObjectMapper()).writeValueAsString(fireStation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/firestation")
                .param("address", "")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateFireStationAddressBlankTest() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("\"8 rue de la paix");
        fireStation.setStation(1);

        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);

        String content = (new ObjectMapper()).writeValueAsString(fireStation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/firestation")
                .param("address", " ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    /*-------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------delete test---------------------------------------------------*/

    @Test
    void deleteFireStationBlankAddressTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", " ")
                .param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(paramResult)
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void deleteFireStationAddressEmptyAndStationNullTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "")
                .param("station", (String) null);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(paramResult)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteFireStationAddressEmptyTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "")
                .param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(paramResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteFireStationStationNullTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "abc")
                .param("station", (String) null);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(paramResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteFireStationStationTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "abc")
                .param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(paramResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}