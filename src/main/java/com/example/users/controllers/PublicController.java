package com.example.users.controllers;


import com.example.users.beans.Response;
import com.example.users.services.AuthService;
import com.example.users.utils.ExceptionHandlerUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/public")
public class PublicController {

    @Autowired
    private AuthService authService;

    @GetMapping("/api-doc")
    public void getApiDoc(HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Location", "http://localhost:8080/swagger-ui/index.html");
        httpServletResponse.setStatus(302);;
    }

    @PostMapping("/register")
    @ResponseBody
    public Response register(@RequestBody Map<String, Object> requestParameter, HttpServletResponse response){
       return ExceptionHandlerUtil.exceptionHandler(authService, requestParameter, response, "register");
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(@RequestBody Map<String, Object> requestParameter, HttpServletResponse response){
        return ExceptionHandlerUtil.exceptionHandler(authService, requestParameter, response, "login");
    }

    @PostMapping("/updateAccessToken")
    @ResponseBody
    public Response updateAccessToken(@RequestBody Map<String, Object> requestParameter, HttpServletResponse response){
        return ExceptionHandlerUtil.exceptionHandler(authService, requestParameter, response, "uploadAccessToken");
    }


}
