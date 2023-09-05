package com.example.users.components.services;

import com.example.users.beans.Response;
import com.example.users.data.entities.RefreshToken;
import com.example.users.data.entities.User;
import com.example.users.utils.exceptions.BadRequestException;
import com.example.users.utils.exceptions.NotFoundException;
import com.example.users.utils.global.variables.UserGlobalVariable;
import com.example.users.data.repositories.RefreshTokenJpa;
import com.example.users.data.repositories.UserJpa;
import com.example.users.data.sql.SQLQueryCountAndExists;
import com.example.users.utils.JWTUtils;
import com.example.users.utils.PasswordUtil;
import com.example.users.utils.RequestBodyParamsUtils;
import com.example.users.utils.validators.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class AuthService {

    @Autowired
    private UserJpa userJpa;
    @Autowired
    private SQLQueryCountAndExists queryCountAndExists;

    @Autowired
    private RefreshTokenJpa refreshTokenJpa;
    public Response register(Map<String, Object> requestBodyParams, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        UserValidator<User> validator = new UserValidator(requestBodyParams, user, queryCountAndExists, "password", "email", "username");
        if(!validator.isValid()){
            throw new BadRequestException(validator.getErrorMessages());
        }
        user.setPassword(PasswordUtil.getHashedPasswordSHA256(user.getPassword()));
        user.setEmailVerified(UserGlobalVariable.EmailNotVerified);
        user.setLastLogin(new Date());
        user.setCreatedTime(new Date());

        userJpa.save(user);

        return Response.createSuccessResponse(user.getMap());
    }
    public Response login(Map<String, Object> requestBodyParams, HttpServletRequest request,  HttpServletResponse response){
        String usernameOrEmail = RequestBodyParamsUtils.getString(requestBodyParams, "usernameOrEmail", true, true);
        String password = RequestBodyParamsUtils.getString(requestBodyParams, "password", true, true);

        User user = getUserByUsernameOrEmail(usernameOrEmail);
        if(user == null)
            throw new NotFoundException();
        if(!PasswordUtil.check_password(user, password))
            throw new NotFoundException();
        RefreshToken refreshToken = new RefreshToken(user);
        refreshTokenJpa.save(refreshToken);
        String accessToken = JWTUtils.createJWTAccessToken(refreshToken);
        return Response.createSuccessResponse(Map.of(
            "accessToken", accessToken,
            "uuid", user.getUuid(),
            "refreshToken", refreshToken.getRefreshToken(),
            "user", user.getMap()
        ));
    }

    public Response uploadAccessToken(Map<String, Object> requestBodyParameters, HttpServletRequest request,  HttpServletResponse response){
        String refreshToken = RequestBodyParamsUtils.getString(requestBodyParameters, "refreshToken", true, true);

        RefreshToken refreshTokenModel = refreshTokenJpa.getRefreshTokenModelByRefreshToken(refreshToken);
        if(refreshTokenModel == null)
            throw new NotFoundException();
        String accessToken = JWTUtils.createJWTAccessToken(refreshTokenModel);
        return Response.createSuccessResponse(Map.of(
                "accessToken", accessToken
        ));
    }

    private User getUserByUsernameOrEmail(String usernameOrEmail){
        User user = null;

        try{
            user = userJpa.getUserModelByUsername(usernameOrEmail);
        }catch (Exception e){}

        if(user != null) return user;

        try {
            user = userJpa.getUserModelByEmail(usernameOrEmail);
        }catch (Exception e){}

        return user;
    }
}
