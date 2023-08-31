package com.example.users.controllers;

import com.example.users.services.TestService;
import com.example.users.test.components.TestComponent;
import com.example.users.utils.Middleware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/test")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private Middleware middleware;

    @PostMapping("/list")
    @ResponseBody
    public Object list(@RequestBody Map<String, Object> parameters, HttpServletResponse response, HttpServletRequest request){
        return middleware.handle(testService, parameters, request, response, "list");
    }
}
