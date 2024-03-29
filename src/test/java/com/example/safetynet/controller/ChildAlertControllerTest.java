package com.example.safetynet.controller;

import com.example.safetynet.dto.ChildAlert;
import com.example.safetynet.model.MedicalRecord;
import com.example.safetynet.service.ChildAlertServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChildAlertController.class)
class ChildAlertControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ChildAlertServiceImp childAlertServiceImp;

    /*----------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------GetTest------------------------------------------------------*/

    @Test
    public void getChildByAddressTest() throws Exception {
        List<String> family = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord("Roger", "Boyd", LocalDate.now().minusYears(5), List.of(), List.of());
        ChildAlert childAlert = new ChildAlert(medicalRecord, family);
        List<ChildAlert> childAlertList = new ArrayList<>();
        childAlertList.add(childAlert);
        when(childAlertServiceImp.getChildByAddress("1509 Culver St")).thenReturn(childAlertList);

        mvc.perform(MockMvcRequestBuilders.get("/childAlert")
                        .param("address", "1509 Culver St"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void getChildByAddressTestWithIncorrectParamName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/childAlert")
                        .param("a", "1509 Culver St")
                        .content("{\"firstName\": \"Test\",\"lastName\": \"\",\"age\": \"" +
                                "\",\"family\": \"\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(400))
                .andExpect(status().reason("Required request parameter 'address' for method parameter type String is not present"));
    }

    // Test the getChildByAddress method when the request parameter value is incorrect
    // It must return a 200 status and a json array containing the error message
    @Test
    public void getChildByAddressTestWithIncorrectParamValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/childAlert")
                        .param("address", "")
                        .content("{\"firstName\": \"Test\",\"lastName\": \"\",\"age\": \"" +
                                "\",\"family\": \"\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}