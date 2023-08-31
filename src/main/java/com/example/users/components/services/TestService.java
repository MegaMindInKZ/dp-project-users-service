package com.example.users.components.services;

import com.example.users.test.components.TestComponent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestService {
    @Autowired
    private TestComponent testComponent;
    public Object list(Map<String, Object> requestBodyParams, HttpServletRequest request, HttpServletResponse response){
        return testComponent.getTestNameString();
    }
}
