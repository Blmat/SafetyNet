package com.example.safetynet.controller;

import com.example.safetynet.service.PersonService;
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


@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PersonService personService;

    String firstNameTest = "John";
    String lastNameTest = "Boyd";

    /*-----------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------AddTest-----------------------------------------------------*/
    @Test
    void addPersonTest() throws Exception {
        this.mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Test\",\"lastName\": \"\",\"address\": \"" +
                                "\",\"city\": \"\",\"zip\": \"\",\"phone\": \"\",\"email\": \"\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    /*-----------------------------------------------------------------------------------------------------------*/
    /*-----------------------------------------UpdateTest-----------------------------------------------------*/
    @Test
    void updatePersonTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", firstNameTest)
                        .param("lastName", lastNameTest)
                        .content("{\"firstName\": \"Test\",\"lastName\": \"\",\"address\": \"" +
                                "\",\"city\": \"\",\"zip\": \"\",\"phone\": \"\",\"email\": \"\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void updatePersonWithFirstNameEmptyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", "")
                        .param("lastName", lastNameTest)
                        .content("{\"firstName\": \"Test\",\"lastName\": \"\",\"address\": \"" +
                                "\",\"city\": \"\",\"zip\": \"\",\"phone\": \"\",\"email\": \"\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }

    @Test
    void updatePersonWithLastNameBlankTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", firstNameTest)
                        .param("lastName", "  ")
                        .content("{\"firstName\": \"Test\",\"lastName\": \"\",\"address\": \"" +
                                "\",\"city\": \"\",\"zip\": \"\",\"phone\": \"\",\"email\": \"\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------DeleteTest------------------------------------------------------*/
    @Test
    public void deletePersonTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/person")
                        .param("firstName", "John")
                        .param("lastName", "Boyd")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"firstName\": \"John\",\"lastName\": \"Boyd\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void deletePersonWithFirstNameEmptyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/person")
                        .param("firstName", "")
                        .param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }

    @Test
    public void deletePersonWithLastNameIsBlankTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/person")
                        .param("firstName", "John")
                        .param("lastName", "  "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }
}