package com.example.users.beans;

import lombok.Data;

@Data
public class Response {
    public Response(){

    }
    public Response(int statusCode, Object result) {
        this.statusCode = statusCode;
        this.result = result;
    }
    private int statusCode;
    private Object result;

    public static Response createSuccessResponse(Object result){
        return new Response(200, result);
    }
}
