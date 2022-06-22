package com.example.safetynet.controller;

import com.example.safetynet.service.PhoneAlertServiceImplement;
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

@WebMvcTest(PhoneAlertController.class)
class PhoneAlertControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PhoneAlertServiceImplement phoneAlertServiceImplement;

    @Test
    void getPhoneByStationTest() throws Exception {
        List<String> stringList = new ArrayList<>();
        stringList.add("0123");

        when(phoneAlertServiceImplement.getPhoneNumberByCoverage("2")).thenReturn(stringList);

        this.mvc.perform(MockMvcRequestBuilders.get("/phoneAlert")
                        .param("firestation", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[\"0123\"]"));
   }
    @Test
    public void getPhoneNumbersByStationTestWithIncorrectParamName() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/phoneAlert")
                        .param("a", "2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getPhoneNumbersByStationTestWithIncorrectParamValue() throws Exception {
        List<String> stringList = new ArrayList<>();

        when(phoneAlertServiceImplement.getPhoneNumberByCoverage("a")).thenReturn(stringList);

        this.mvc.perform(MockMvcRequestBuilders.get("/phoneAlert")
                        .param("firestation", "a"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }
}