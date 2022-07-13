package com.example.safetynet.controller;

import com.example.safetynet.service.PersonInfo;
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

@WebMvcTest(PersonInfoController.class)
public class PersonIntInfoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersonInfo personInfo;


    @Test
    public void getPersonInformationTest() throws Exception {

//        List<PersonInfoDto> personInfoList = new ArrayList<>();
//        personInfoList.add(1, personInfoList.get(2));
//
//        when(personInfo.getPersonInformation("John", "Boyd")).thenReturn((List<PersonInfo>) personInfoList);
//
//        this.mvc.perform(MockMvcRequestBuilders.get("/personInfo")
//                .param("firstName", "John").param("lastName", "Boyd"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":38,\"email\":\"jaboyd@email.com\",\"medications\":null,\"allergies\":null}]"));
    }

    @Test
    public void getPersonInformationTestWithIncorrectParam() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/personInfo")
                        .param("a", "John")
                        .param("lastName", "a"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(status().reason("Required request parameter 'firstName' for method parameter type String is not present"));
    }

    @Test
    public void getPersonInformationTestWithIncorrectParamValue() throws Exception {
        List<PersonInfo> personInfoList = new ArrayList<>();

        when(personInfo.getPersonInformation("", "")).thenReturn((List<PersonInfo>) personInfoList);

        mvc.perform(MockMvcRequestBuilders.get("/personInfo")
                        .param("firstName", "")
                        .param("lastName", ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("[\"The request '' or '' doesn't match anything or is incorrect\"]"));
    }
}
