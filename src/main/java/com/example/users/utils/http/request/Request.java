package com.example.users.utils.http.request;

import lombok.Data;

import java.util.Map;

@Data
public class Request {
    public final static String postMethod = "POST";
    public final static String getMethod = "GET";
    private String method;
    private String uriPath;
    private Map<String, Object> uriParameter;
    private Object content;
    private String contentType;
}
