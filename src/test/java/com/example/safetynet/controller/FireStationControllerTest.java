package com.example.safetynet.controller;

import com.example.safetynet.service.FireStationServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FireStationController.class)
public class FireStationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    FireStationServiceImp fireStationServiceImp;

    /*-----------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------AddTest-----------------------------------------------------*/
    @Test
    public void addFireStationTest() throws Exception {
        mvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\": \"1509 Culver St\",\"station\": \"3\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    /*-----------------------------------------------------------------------------------------------------------*/
    /*-----------------------------------------UpdateTest-----------------------------------------------------*/
    @Test
    void updateFirestationTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/firestation")
                        .param("address", "1509 Culver St")
                        .param("station", "3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\": \"1509 Culver St\",\"station\": \"3\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void updateFireStationWithAddressEmpty() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/firestation")
                        .param("address", "")
                        .param("station", "3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\": \"1509 Culver St\",\"station\": \"3\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
    /*-------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------delete test---------------------------------------------------*/

    @Test
    void deleteFirestation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/firestation")
                        .param("address", "1509 Culver St")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\": \"1509 Culver St\",\"station\": \"3\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationWithAddressEmpty() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/firestation")
                        .param("address", "")
                        .content("{\"address\": \"1509 Culver St\",\"station\": \"3\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteFireStationWithAddressBlank() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/firestation")
                        .param("address", " ")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"address\": \"1509 Culver St\",\"station\": \"3\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}