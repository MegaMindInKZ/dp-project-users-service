package com.example.users.utils.http.response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.util.Map;

@Data
public class Response {
    private int result_code;
    private String content;

    public com.example.users.beans.Response getResponse(){
        com.example.users.beans.Response response = new com.example.users.beans.Response();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> userData = mapper.readValue(
                    content, new TypeReference<Map<String, Object>>() {
                    });

            response.setStatusCode(Integer.parseInt(userData.get("statusCode").toString()));
            response.setResult(userData.get("result"));
        }catch (Exception e){
            response.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setResult(e);
        }
        return response;
    }
}
