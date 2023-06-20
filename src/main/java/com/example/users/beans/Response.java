package com.example.users.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private int resultCode;
    private String resultMessage;
    private Object data;

    public static Response createSimpleSuccessMessage(){
        return new Response(0, "OK", null);
    }
}
