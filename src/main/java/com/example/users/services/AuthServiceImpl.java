package com.example.users.services;

import com.example.users.beans.Response;
import com.example.users.entities.RefreshToken;
import com.example.users.entities.User;
import com.example.users.exceptions.ServiceException;
import com.example.users.global.variables.UserGlobalVariable;
import com.example.users.repositories.RefreshTokenJpa;
import com.example.users.repositories.UserJpa;
import com.example.users.sql.SQLQueryCountAndExists;
import com.example.users.utils.JWTUtils;
import com.example.users.utils.PasswordUtil;
import com.example.users.utils.RequestBodyParamsUtils;
import com.example.users.validators.AbstractValidator;
import com.example.users.validators.client.ClientUserModelRegistrationValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserJpa userJpa;

    @Autowired
    SQLQueryCountAndExists queryCountAndExists;

    @Autowired
    RefreshTokenJpa refreshTokenJpa;
    @Override
    public Response register(Map<String, Object> requestBodyParams, HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ServiceException, ClassNotFoundException {
        User user = new User();
        AbstractValidator<User> validator = new ClientUserModelRegistrationValidator(requestBodyParams, user, queryCountAndExists);
        if(!validator.isValid()){
            return new Response(2, "Validation error!", validator.getErrorMessages());
        }
        user.setPassword(PasswordUtil.getHashedPasswordSHA256(user.getPassword()));
        user.setEmailVerified(UserGlobalVariable.EmailNotVerified);
        user.setLastLogin(new Date());
        user.setCreatedTime(new Date());

        userJpa.save(user);

        return new Response(1, "OK", user.getMap());
    }
    @Override
    public Response login(Map<String, Object> requestBodyParams, HttpServletRequest request,  HttpServletResponse response) throws ServiceException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String usernameOrEmail = RequestBodyParamsUtils.getString(requestBodyParams, "usernameOrEmail", true, true);
        String password = RequestBodyParamsUtils.getString(requestBodyParams, "password", true, true);

        User user = getUserByUsernameOrEmail(usernameOrEmail);
        if(user == null)
            return new Response(2, "input error!", Map.of("errors", new ArrayList<String>(){{add("username or password is not correct!");}}));
        if(!PasswordUtil.check_password(user, password))
            return new Response(2, "input error!", new ArrayList<String>(){{add("username or password is not correct!");}});
        RefreshToken refreshToken = new RefreshToken(user);
        refreshTokenJpa.save(refreshToken);
        String accessToken = JWTUtils.createJWTAccessToken(refreshToken);
        return new Response(1, "OK", Map.of(
            "accessToken", accessToken,
            "uuid", user.getUuid(),
            "refreshToken", refreshToken.getRefreshToken(),
            "user", user.getMap()
        ));
    }

    @Override
    public Response uploadAccessToken(Map<String, Object> requestBodyParameters, HttpServletRequest request,  HttpServletResponse response) throws ServiceException {
        String refreshToken = RequestBodyParamsUtils.getString(requestBodyParameters, "refreshToken", true, true);

        RefreshToken refreshTokenModel = refreshTokenJpa.getRefreshTokenModelByRefreshToken(refreshToken);
        if(refreshTokenModel == null)
            throw new ServiceException(3, "Auth Error!");
        String accessToken = JWTUtils.createJWTAccessToken(refreshTokenModel);
        return new Response(1, "OK", Map.of(
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
