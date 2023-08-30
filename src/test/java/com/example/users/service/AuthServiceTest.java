package com.example.users.service;

import com.example.users.entities.User;
import com.example.users.exceptions.BadRequestException;
import com.example.users.repositories.RefreshTokenJpa;
import com.example.users.repositories.UserJpa;
import com.example.users.services.AuthService;
import com.example.users.sql.SQLQueryCountAndExists;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthServiceTest {
    @Mock
    private UserJpa userJpa;
    @Mock
    private SQLQueryCountAndExists queryCountAndExists;
    @Mock
    private RefreshTokenJpa refreshTokenJpa;
    @InjectMocks
    private AuthService authService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test(expected = BadRequestException.class)
    public void nullUsernameShouldThrowBadRequestException(){
        User user = new User();
        user.setPassword("123456789");
        user.setEmail("2134");


        authService.register(objectMapper.convertValue(user, new TypeReference<Map<String, Object>>() {}), null, null);
    }
}
