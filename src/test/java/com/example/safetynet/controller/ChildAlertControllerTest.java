package com.example.safetynet.controller;

import com.example.safetynet.service.ChildAlertServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ChildAlertController.class)
class ChildAlertControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ChildAlertServiceImplement childAlertServiceImplement;

   

    }