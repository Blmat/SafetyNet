package com.example.safetynet.controller;

import com.example.safetynet.model.ChildAlert;
import com.example.safetynet.service.ChildAlertServiceImplement;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ChildAlertControllerTest {

    @Autowired
    private ChildAlertController childAlertController;

    @MockBean
    private ChildAlertServiceImplement childAlertServiceImplement;

    static ChildAlert childAlert;


    }