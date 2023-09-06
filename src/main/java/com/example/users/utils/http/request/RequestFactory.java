package com.example.users.utils.http.request;

import com.example.users.utils.http.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class RequestFactory {
    @Value("${server.port}")
    private int serverPort;

    public Response getRequest(Request request){

        request.setMethod(Request.getMethod);
        try {
            return request(request);
        }catch (Exception ignored){
            throw new RuntimeException(ignored);
        }
    }

    public Response postRequest(Request request){
        request.setMethod(Request.postMethod);
        try {
            return request(request);
        }catch (Exception ignored){
            throw new RuntimeException(ignored);
        }
    }
    private Response request(Request request) throws IOException {
        final int timeoutSeconds = 4;

        URL url = new URL("http://localhost:" + serverPort + request.getUri());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(request.getMethod());
        connection.setRequestProperty("Content-Type", request.getContentType());
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setReadTimeout(timeoutSeconds * 1000);
        connection.setConnectTimeout(timeoutSeconds * 1000);

        String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request.getContent());

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Response response = new Response();
        response.setResult_code(connection.getResponseCode());


        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder responseBuilder = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                responseBuilder.append(responseLine.trim());
            }
            response.setContent(responseBuilder.toString());
        } catch (Exception ignored) {
        }
        return response;
    }
}
