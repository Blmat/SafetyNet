package com.example.safetynet.controller;

import com.example.safetynet.dto.PersonInfoDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonInfoController.class)
public class PersonIntInfoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersonInfo personInfo;


    @Test
    public void getPersonInformationTest() throws Exception {

        PersonInfoDto pi = new PersonInfoDto("John", "Boyd", "000", 15, "000", null, null);
        List<PersonInfoDto> personInfoList = new ArrayList<>();
        personInfoList.add(pi);

        when(personInfo.getPersonInformation("John", "Boyd")).thenReturn(personInfoList);

        mvc.perform(MockMvcRequestBuilders.get("/personInfo")
                        .param("firstName", "John").param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
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

        mvc.perform(MockMvcRequestBuilders.get("/personInfo")
                        .param("firstName", "")
                        .param("lastName", ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}
