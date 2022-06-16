package com.example.safetynet.controller;

import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.service.MedicalRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MedicalRecordControllerTest {

    @Autowired
   private MedicalRecordController medicalRecordController;

    @MockBean
    private MedicalRecordService medicalRecordService;

    static MedicalRecord medicalRecord;

    @BeforeEach
    private void init() {
        medicalRecord = new MedicalRecord("Guy", "Lee",
                LocalDate.of(1970,12,12), new ArrayList<>(), new ArrayList<>());
    }

    /*----------------------------------------------------------------------------------------------------------*/
     /*-------------------------------------------AddTest------------------------------------------------------*/

    @Test
    void addMedicalRecordTest() throws Exception {
        when(this.medicalRecordService.addMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"" + "firstName\":\"Guy\"," + "\"lastName\":\"Lee\"," +
                                "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" +
                                "}"));
    }

    /*-----------------------------------------------------------------------------------------------------------*/
    /*-----------------------------------------UpdateTest-----------------------------------------------------*/
    @Test
    void updateAMedicalRecordTest() throws Exception {
        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
                .param("firstName", "Guy")
                .param("lastName", "Lee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"" + "firstName\":\"Guy\"," + "\"lastName\":\"Lee\"," +
                                "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" +
                                "}"));
    }

    @Test
    void updateAMedicalRecordFirstNameBlankTest() throws Exception {
        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
                .param("firstName", " ")
                .param("lastName", "Lee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateAMedicalRecordLastNameBlankTest() throws Exception {
        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
                .param("firstName", "Guy")
                .param("lastName", " ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void updateAMedicalRecordFirstNameEmptyTest() throws Exception {
        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
                .param("firstName", "")
                .param("lastName", "Lee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void updateAMedicalRecordLastNameEmptyTest() throws Exception {
        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
                .param("firstName", "Guy")
                .param("lastName", "")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }



    /*----------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------DeleteTest------------------------------------------------------*/
    @Test
    void deleteMedicalRecordTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalRecord")
                .param("firstName", "Guy")
                .param("lastName", "Lee");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecordFirstNameBlankTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalRecord")
                .param("firstName", " ")
                .param("lastName", "Lee");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMedicalRecordLastNameBlankTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalRecord")
                .param("firstName", "Guy")
                .param("lastName", " ");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMedicalRecordFirstNameIsEmptyTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalRecord")
                .param("firstName", "")
                .param("lastName", "Lee");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMedicalRecordLastNameIsEmptyTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalRecord")
                .param("firstName", "Guy")
                .param("lastName", "");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

}