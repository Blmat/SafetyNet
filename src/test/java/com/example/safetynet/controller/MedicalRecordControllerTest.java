package com.example.safetynet.controller;

import com.example.safetynet.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    private MedicalRecordService medicalRecordService;



    /*----------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------AddTest------------------------------------------------------*/
    @Test
    void addMedicalRecordTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"" + "firstName\":\"Test\"," + "\"lastName\":\"test\"," + "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" + "}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    /*-----------------------------------------------------------------------------------------------------------*/
    /*-----------------------------------------UpdateTest-----------------------------------------------------*/
    @Test
    void updateAMedicalRecordTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", "John")
                        .param("lastName", "Boyd")
                        .content("{\"" + "firstName\":\"Test\"," + "\"lastName\":\"test\"," +
                                "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" +
                                "}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void updateAMedicalRecordWithFirstNameEmptyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", "")
                        .param("lastName", "Boyd")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"" + "firstName\":\"Test\"," + "\"lastName\":\"test\"," +
                                "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" +
                                "}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }

    @Test
    void updateAMedicalRecordWithLastNameBlankTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", "John")
                        .param("lastName", "  ")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"" + "firstName\":\"Test\"," + "\"lastName\":\"test\"," +
                                "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" +
                                "}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }



    /*----------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------DeleteTest------------------------------------------------------*/
    @Test
    void deleteMedicalRecordTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                        .param("firstName", "John")
                        .param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecordWithFirstNameEmptyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                        .param("firstName", "")
                        .param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }

    @Test
    public void deleteMedicalRecordWithLastNameBlankTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                        .param("firstName", "John")
                        .param("lastName", "  "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }
}