package com.example.users.utils.test.controller;

import com.example.users.beans.Response;
import com.example.users.components.controllers.PublicController;
import com.example.users.data.entities.User;
import com.example.users.data.repositories.UserJpa;
import com.example.users.utils.http.request.Request;
import com.example.users.utils.http.request.RequestFactory;
import com.example.users.utils.test.annotations.*;
import com.example.users.utils.test.exceptions.NotPassedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Test
public class TestPublicController {
    @Autowired
    private PublicController publicController;
    @Autowired
    private UserJpa userJpa;
    @Autowired
    private RequestFactory requestFactory;
    private final String requestCommonURIPath = "/users/public";
    ObjectMapper objectMapper = new ObjectMapper();

    private User user;

    @BeforeTestClass
    public void createUser(){
        System.out.println("before");
    }

    @Test
    public void register(){
        user = new User();
        user.setUsername("123456789d");
        user.setPassword("kazsadfakh1");
        user.setFullname("Kazakh");

        System.out.println("test");

        Request request = new Request();
        request.setMethod(Request.postMethod);
        request.setContentType("application/json");
        request.setUriPath(requestCommonURIPath + "/register");

        request.setContent(user);

        Response response = null;
        try {
            response = requestFactory.request(request);
        }catch (Exception e){
        }
        if(response.getStatusCode() != HttpServletResponse.SC_BAD_REQUEST)
            throw new NotPassedException();
        if(!response.getResult().toString().contains("sd"))
            throw new NotPassedException();
    }

    @AfterTestClass
    public void deleteUser(){
        userJpa.delete(user);
    }
}
