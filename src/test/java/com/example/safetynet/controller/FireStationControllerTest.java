package com.example.safetynet.controller;

import com.example.safetynet.model.FireStation;
import com.example.safetynet.service.FireStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FireStationControllerTest {

    @Autowired
    private FireStationController fireStationController;

    @MockBean
    private FireStationService fireStationService;

/*---------------------------------------delete test---------------------------------------------------*/
    @Test
    void deleteFireStationAddressBlankTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", " ");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    void deleteFireStationAddressEmptyAndStationNullTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", (String) null);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void deleteFireStationAddressEmptyTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void deleteFireStationStationNullTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "abc");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", (String) null);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void deleteFireStationStationTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "abc");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
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
    /*----------------------------------------------------------------------------------------------------------*/
     /*-------------------------------------------AddTest------------------------------------------------------*/



}