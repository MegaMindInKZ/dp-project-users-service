package com.example.users.components.controllers;


import com.example.users.components.services.AuthService;
import com.example.users.utils.Middleware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/public")
public class PublicController {
    @Autowired
    private Middleware middleware;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestBody Map<String, Object> requestParameter, HttpServletRequest request, HttpServletResponse response){
       return middleware.handle(authService, requestParameter, request, response, "register");
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody Map<String, Object> requestParameter, HttpServletRequest request,  HttpServletResponse response){
        return middleware.handle(authService, requestParameter, request, response, "login");
    }

    @PostMapping("/access-token")
    @ResponseBody
    public Object updateAccessToken(@RequestBody Map<String, Object> requestParameter, HttpServletRequest request,  HttpServletResponse response){
        return middleware.handle(authService, requestParameter, request, response, "uploadAccessToken");
    }


}
