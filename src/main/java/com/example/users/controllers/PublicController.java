package com.example.users.controllers;


import com.example.users.beans.Response;
import com.example.users.services.AuthService;
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
    private AuthService authService;

    @PostMapping("/register")
    @ResponseBody
    public Response register(@RequestBody Map<String, Object> requestParameter, HttpServletRequest request, HttpServletResponse response){
       return Middleware.handle(authService, requestParameter, request, response, "register");
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(@RequestBody Map<String, Object> requestParameter, HttpServletRequest request,  HttpServletResponse response){
        return Middleware.handle(authService, requestParameter, request, response, "login");
    }

    @PostMapping("/updateAccessToken")
    @ResponseBody
    public Response updateAccessToken(@RequestBody Map<String, Object> requestParameter, HttpServletRequest request,  HttpServletResponse response){
        return Middleware.handle(authService, requestParameter, request, response, "uploadAccessToken");
    }


}
