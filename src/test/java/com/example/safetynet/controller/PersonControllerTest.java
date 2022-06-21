package com.example.safetynet.controller;

import com.example.safetynet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
//    @Test
//     void updatePersonTest() throws Exception {
//        mvc.perform(put("/person")
//                        .param("firstname", "John")
//                        .param("lastname", "Boyd")
//                        .param("address","1509 Culver St")
//                        .param("city", "Culver")
//                        .param("zip", "97451")
//                        .param("phone","841-874-6512")
//                        .param("email","jaboyd@email.com")
//                        .contentType(MediaType.APPLICATION_JSON).content("{\"firstName\": \"John\",\"lastName\": " +
//                                "\"Boyd\",\"address\": \"1509 Culver St\",\"city\": \"Culver\",\"zip\": \"97451\"," +
//                                "\"phone\": \"841-874-6512\",\"email\": \"jaboyd@email.com\"}"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is2xxSuccessful());
//    }
    @Test
    void updatePersonValid() throws Exception {

        ObjectMapper obm = new ObjectMapper();
        ObjectNode objectNode = obm.createObjectNode();

        // GIVEN

        objectNode.set("firstName", TextNode.valueOf(firstNameTest));
        objectNode.set("lastName", TextNode.valueOf(lastNameTest));

        // WHEN
        // THEN

        mvc.perform(MockMvcRequestBuilders.put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectNode.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    /*--------------------------------------------------------------------------------------------------------------*/
     /*-------------------------------------------DeleteTest------------------------------------------------------*/
    @Test
    public void deletePersonTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/person")
                        .param("firstName", "John").param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    @Test
    public void deletePersonWithFirstNameEmptyTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/person")
                        .param("firstName", "").param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }

    @Test
    public void deletePersonWithLastNameIsBlankTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/person")
                        .param("firstName", "John").param("lastName", "  "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }
}