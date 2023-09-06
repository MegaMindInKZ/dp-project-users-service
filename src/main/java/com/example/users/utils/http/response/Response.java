package com.example.users.utils.http.response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Map;

@Data
public class Response {
    private int result_code;
    private String content;

    public Map<String, Object> getResult(){

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> resultMap = mapper.readValue(
                    content, new TypeReference<Map<String, Object>>() {
                    });
            return resultMap;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
