package com.example.users.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.users.entities.RefreshTokenModel;
import com.example.users.global.variables.UserGlobalVariable;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String secretKey = "T8A7fsSWfwuEKMJnzzz9CPtF3InJ645hjl02Wc3rNXpTvvPcRY8QcZxEvwZeAjSwdmmEZNwgxYDgDXsKemiqmhUlDUrYPmrvsSsorlCjEdOXX3CyVP1DcDvnpFYh1M8o";
    public static String createJWTAccessToken(RefreshTokenModel refreshTokenModel) {
        Map<String, Object> payload = createPayload(refreshTokenModel);
        try {
            JWTCreator.Builder builder = JWT.create();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, UserGlobalVariable.JWTAccessTokenExpirationTimeMinute);
            payload.forEach((k, v)->{
                builder.withClaim(k, v.toString());
            });
            String accessToken = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(secretKey));
            return accessToken;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> createPayload(RefreshTokenModel refreshTokenModel){
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", refreshTokenModel.getUsername());
        payload.put("email", refreshTokenModel.getEmail());
        payload.put("userID", refreshTokenModel.getUserID());
        return payload;
    }

    public static DecodedJWT verify(String token)
    {
        if(token==null)
            return null;
        DecodedJWT decodedJWT= JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        return decodedJWT;
    }
}
