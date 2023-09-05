package com.example.users.utils.http.request;

import com.example.users.utils.http.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class RequestFactory {
    private final int timeoutSeconds = 4;
    @Value("${server.port}")
    private int serverPort;
    public com.example.users.beans.Response request(Request request) throws IOException {
        URL url = new URL("http://localhost:" + serverPort + request.getUriPath());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(request.getMethod());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request.getContent());

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        Response response = new Response();
        response.setResult_code(connection.getResponseCode());


        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder responseBuilder = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                responseBuilder.append(responseLine.trim());
            }
            response.setContent(responseBuilder.toString());
        }catch (Exception e){
        }
        return response.getResponse();
    }

    private HttpUriRequest convertToHttpUriRequest(Request request) throws UnsupportedEncodingException, JsonProcessingException {
        HttpEntityEnclosingRequestBase requestBase = null;
        String uri = "http://localhost:" + serverPort + request.getUriPath();

        switch (request.getMethod()){
            case Request.postMethod -> {
                requestBase = new HttpPost(uri);
            }
        }

        requestBase.addHeader("Content-type", request.getContentType());

        List<NameValuePair> nameValuePairs = new ArrayList<>();

        for(var entry: request.getUriParameter().entrySet()) {
            if(entry.getValue() == null) continue;
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }

        requestBase.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        return requestBase;
    }
}
