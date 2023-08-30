package com.example.users.controller;

import com.example.users.controllers.PublicController;
import com.example.users.entities.User;
import com.example.users.repositories.RefreshTokenJpa;
import com.example.users.repositories.UserJpa;
import com.example.users.services.AuthService;
import com.example.users.sql.SQLQueryCountAndExists;
import com.example.users.utils.Middleware;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicController.class)
public class PublicControllerTest {
    public static final String ENP_POINT_PATH = "/users/public";
    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean AuthService authService;
    @MockBean Middleware middleware;
    @Test
    public void registerShouldReturn200() throws Exception {
        User user = new User();
        user.setUsername("aadf");

        String newUserJSON = objectMapper.writeValueAsString(user);

        RequestBuilder request = MockMvcRequestBuilders
                .post(ENP_POINT_PATH + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(newUserJSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }
    @Test
    public void loginShouldReturn200() throws Exception {
        User user = new User();
        user.setUsername("aadf");

        String newUserJSON = objectMapper.writeValueAsString(user);

        RequestBuilder request = MockMvcRequestBuilders
                .post(ENP_POINT_PATH + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(newUserJSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }
    @Test
    public void updateAccessTokenShouldReturn200() throws Exception {
        User user = new User();
        user.setUsername("aadf");

        String newUserJSON = objectMapper.writeValueAsString(user);

        RequestBuilder request = MockMvcRequestBuilders
                .post(ENP_POINT_PATH + "/access-token")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(newUserJSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }
}
