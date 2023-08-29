package com.example.users.controller;

import com.example.users.controllers.PublicController;
import com.example.users.entities.User;
import com.example.users.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PublicController.class)
public class PublicControllerTest {
    private static final String END_POINT_PATH = "/users/public";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthService authService;
    private User newUser;

    @BeforeTestClass
    public void createNewUser(){
        newUser = new User();
        newUser.setEmail("megamindinkz@gmail.com");
        newUser.setPassword("asdfja;@rawsfhasjhfgsdlasdf23453w");
        newUser.setFullname("Zanggar Zhumagaliyev");
        newUser.setRole("asdfqwer");
    }

    @Test
    public void registerShouldReturn200(){

    }
}
