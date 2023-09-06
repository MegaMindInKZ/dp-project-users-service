package com.example.users.utils.test.controller;

import com.example.users.data.entities.User;
import com.example.users.data.repositories.UserJpa;
import com.example.users.utils.exceptions.NotFoundException;
import com.example.users.utils.http.request.Request;
import com.example.users.utils.http.request.RequestFactory;
import com.example.users.utils.http.response.Response;
import com.example.users.utils.test.annotations.*;
import com.example.users.utils.test.assertions.Assertion;
import com.example.users.utils.test.exceptions.NotPassedException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@Test
public class TestPublicController {
    @Autowired
    private UserJpa userJpa;
    @Autowired
    private RequestFactory requestFactory;
    private final String requestCommonURIPath = "/users/public";
    private User user;

    @BeforeTestClass
    public void createUser(){
        user = new User();
        user.setUsername("1234");
        user.setPassword("1234");
        user.setEmail("email");
        user.setFullname("Kazakh");

        userJpa.save(user);
    }

    @Test
    public void registerShouldBeSuccessful(){
        User user = new User();
        user.setUsername("Zanggar");
        user.setPassword("12345678");
        user.setFullname("Zanggar Zhumagaliyev");
        user.setEmail("megamindinkz@gmail.com");

        Request request = new Request();
        request.setUriPath(requestCommonURIPath + "/register");
        request.setContent(user);

        Response response = requestFactory.postRequest(request);

        Assertion.assertEquals(response.getResult_code(), HttpServletResponse.SC_OK);

        user = userJpa.getUserModelByUsername(user.getUsername());
        userJpa.delete(user);
    }

    @Test
    public void registerBadRequestAndEmailInvalid(){
        Request request = new Request();
        request.setUriPath(requestCommonURIPath + "/register");

        request.setContent(user);

        Response response = requestFactory.postRequest(request);

        Assertion.assertEquals(response.getResult_code(), HttpServletResponse.SC_BAD_REQUEST);
        Assertion.assertContains(response.getContent(), "email");
    }

    @Test
    public void registerBadRequestAndUsernameInvalid(){
        Request request = new Request();
        request.setUriPath(requestCommonURIPath + "/register");

        request.setContent(user);

        Response response = requestFactory.postRequest(request);

        Assertion.assertEquals(response.getResult_code(), HttpServletResponse.SC_BAD_REQUEST);
        Assertion.assertContains(response.getContent(), "username");

    }

    @Test
    public void registerBadRequestAndPasswordInvalid(){
        Request request = new Request();
        request.setUriPath(requestCommonURIPath + "/register");

        request.setContent(user);

        Response response = requestFactory.postRequest(request);

        Assertion.assertEquals(response.getResult_code(), HttpServletResponse.SC_BAD_REQUEST);
        Assertion.assertContains(response.getContent(), "password");
    }

    @AfterTestClass
    public void deleteUser(){
        User temp = userJpa.getUserModelByUsername(user.getUsername());
        userJpa.delete(temp);
    }
}
