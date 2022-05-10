package com.example.safetynet.controller;

import com.example.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;
    @MockBean
    private PersonService personService;

    @Test
    void getPersonTest() throws Exception {
        when(this.personService.getPersons()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person");
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }



}