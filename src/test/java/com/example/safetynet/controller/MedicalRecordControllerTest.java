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
        this.mvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"" + "firstName\":\"Test\"," + "\"lastName\":\"test\"," +
                        "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" +
                        "}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }
    /*-----------------------------------------------------------------------------------------------------------*/
    /*-----------------------------------------UpdateTest-----------------------------------------------------*/
//    @Test
//    void updateAMedicalRecordTest() throws Exception {
//        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);
//
//        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
//                .param("firstName", "Guy")
//                .param("lastName", "Lee")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("{\"" + "firstName\":\"Guy\"," + "\"lastName\":\"Lee\"," +
//                        "\"birthdate\":\"12/12/1970\"," + "\"medications\":[]," + "\"allergies\":[]" +
//                        "}"));
//    }
//
//    @Test
//    void updateAMedicalRecordFirstNameBlankTest() throws Exception {
//        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);
//
//        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
//                .param("firstName", " ")
//                .param("lastName", "Lee")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    void updateAMedicalRecordLastNameBlankTest() throws Exception {
//        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);
//
//        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
//                .param("firstName", "Guy")
//                .param("lastName", " ")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void updateAMedicalRecordFirstNameEmptyTest() throws Exception {
//        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);
//
//        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
//                .param("firstName", "")
//                .param("lastName", "Lee")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void updateAMedicalRecordLastNameEmptyTest() throws Exception {
//        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);
//
//        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalRecord")
//                .param("firstName", "Guy")
//                .param("lastName", "")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(status().isNotFound());
//    }
//
//
//
    /*----------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------DeleteTest------------------------------------------------------*/
    @Test
    void deleteMedicalRecordTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                        .param("firstName", "John")
                        .param("lastName", "Boyd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    @Test
    public void deleteMedicalRecordWithFirstNameEmptyTest() throws Exception {
       this.mvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
               .param("firstName", "")
                        .param("lastName", "Boyd"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is(404));
}
    @Test
    public void deleteMedicalRecordWithLastNameBlankTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
                        .param("firstName", "John")
                        .param("lastName", "  "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(404));
    }


}