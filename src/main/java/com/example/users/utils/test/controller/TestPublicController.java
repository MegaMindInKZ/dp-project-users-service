package com.example.users.utils.test.controller;

import com.example.users.components.controllers.PublicController;
import com.example.users.data.entities.User;
import com.example.users.data.repositories.UserJpa;
import com.example.users.utils.test.annotations.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Test
public class TestPublicController {
    @Autowired
    private PublicController publicController;
    @Autowired
    private UserJpa userJpa;
    ObjectMapper objectMapper = new ObjectMapper();

    private User user;

    @BeforeTestClass
    public void createUser(){
        user = new User();
        user.setUsername("123456789");
        user.setPassword("kazsadfakh");
        user.setFullname("Kazakh");



        Map<String, Object> parameters = objectMapper.convertValue(user, Map.class);
        publicController.register(parameters, null, null);
    }

    @AfterTestClass
    public void deleteUser(){
        userJpa.delete(user);
    }
}
