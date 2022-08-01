package com.example.safetynet.controller;

import com.example.safetynet.service.CommunityEmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommunityEmailController.class)
public class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    CommunityEmailServiceImp communityEmailService;

    // Test the getEmailsByCity method when the request is correct
    // It must return a 200 status and json array the response
    @Test
    public void getEmailTestByCity() throws Exception {
        List<String> email = new ArrayList<>();
        email.add("john.doe@testmail.com");
        when(communityEmailService.getEmailByCity("Culver")).thenReturn(email);

        mvc.perform(MockMvcRequestBuilders.get("/communityEmail")
                        .param("city", "Culver"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    // Test the getEmailsByCity method when the request parameter name is incorrect
    // It must return a 400 status and an error message
    @Test
    public void getEmailTestByCityWithIncorrectParamName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/communityEmail")
                        .param("a", "Culver"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Required request parameter 'city' for method parameter type String is not present"));
    }

    // Test the getEmailsByCity method when the request parameter value is incorrect
    // It must return a 200 status and a json array containing the error message
    @Test
    public void getEmailTestByCityWithIncorrectParamValue() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/communityEmail")
                        .param("city", "Test"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }
}