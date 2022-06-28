package com.example.safetynet.controller;

import com.example.safetynet.model.PersonInfo;
import com.example.safetynet.service.PersonInfoImplement;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonInfoController.class)
public class PersonInfoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersonInfoImplement personInfoImplement;


    @Test
    public void getPersonInformationTest() throws Exception {
        List<PersonInfo> personInfoList = new ArrayList<>();
        PersonInfo pi = new PersonInfo("John", "Boyd", "1509 Culver St", 38, null, null, null);
        personInfoList.add(pi);

        when(personInfoImplement.getPersonInformation("John", "Boyd")).thenReturn((PersonInfo) personInfoList);

        mvc.perform(MockMvcRequestBuilders.get("/personInfo")
                        .param("firstName", "John")
                        .param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getPersonInformationTestWithIncorrectParamName() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/personInfo")
                        .param("a", "John")
                        .param("lastname", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void getPersonInformationTestWithIncorrectParamValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/personInfo")
                        .param("firstName", "a")
                        .param("lastname","Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }
}
