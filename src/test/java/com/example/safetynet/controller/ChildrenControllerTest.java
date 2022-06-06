//package com.example.safetynet.controller;
//
//import com.example.safetynet.model.Children;
//import com.example.safetynet.service.ChildrenService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ChildrenController.class)
//@ExtendWith(SpringExtension.class)
//public class ChildrenControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ChildrenService childrenService;
//
//    @Test
//    public void getChildByAddressTest() throws Exception {
//
//        List<String> family = new ArrayList<>();
//        Children children = new Children("eee", "eee", 15, family);
//        List<Children> childrenList = new ArrayList<>();
//        childrenList.add(children);
//        when(childrenService.getChildrenByAddress("1509 Culver St")).thenReturn(childrenList);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/children")
//                        .param("address", "1509 Culver St"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect((ResultMatcher) content().json("[{\"firstName\":\"eee\",\"lastName\":\"eee\",\"age\":15,\"family\":[]}]"));
//    }
//
//}
