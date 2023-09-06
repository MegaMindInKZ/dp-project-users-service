package com.example.users.utils.http.request;

import lombok.Data;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@Data
public class Request {
    public final static String postMethod = "POST";
    public final static String getMethod = "GET";
    private String method;
    private String uriPath;
    private Map<String, Object> uriParameter = new HashMap<>();
    private Object content;
    private String contentType = MediaType.APPLICATION_JSON_VALUE;

    public String getUri(){
       String result = uriPath + "?";
       for(var entry: uriParameter.entrySet()){
           result = result + entry.getKey() + "=" + entry.getValue().toString();
       }
       return result;
    }
}
