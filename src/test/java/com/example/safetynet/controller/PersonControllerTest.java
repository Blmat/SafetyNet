package com.example.safetynet.controller;

import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;
    @MockBean
    private PersonService personService;

    static Person person;

    @BeforeEach
    private void init() {
        person = new Person("Guy", "Lee", "01 rue de sa maison",
                "Strasbourg", "01234", "0800700", "blablabla@yopmail.fr");
    }


    @Test
    void getPersonTest() throws Exception {
        when(personService.getPersons()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person");
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
        verify(personService, times(1)).getPersons();
    }

    @Test
    void addPersonTest() throws Exception {

        when(this.personService.addPerson(any(Person.class))).thenReturn(person);

        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"" +
                                "firstName\":\"Guy\"," +
                                "\"lastName\":\"Lee\"," +
                                "\"address\":\"01 rue de sa maison\"," +
                                "\"city\":\"Strasbourg\"," +
                                "\"zip\":\"01234\"," +
                                "\"phone\":\"0800700\"," +
                                "\"email\":\"blablabla@yopmail.fr\"" +
                                "}"));
    }

    @Test
    void uptadeAPerson() throws Exception {

        when(this.personService.updatePerson(any(),any(), any())).thenReturn( person);

        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/person")
                .param("firstName", "Guy")
                .param("lastName", "Lee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"" +
                                "firstName\":\"Guy\"," +
                                "\"lastName\":\"Lee\"," +
                                "\"address\":\"01 rue de sa maison\"," +
                                "\"city\":\"Strasbourg\"," +
                                "\"zip\":\"01234\"," +
                                "\"phone\":\"0800700\"," +
                                "\"email\":\"blablabla@yopmail.fr\"" +
                                "}"));
    }
}