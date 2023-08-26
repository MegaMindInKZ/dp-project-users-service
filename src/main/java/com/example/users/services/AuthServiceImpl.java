package com.example.users.services;

import com.example.users.entities.RefreshToken;
import com.example.users.entities.User;
import com.example.users.exceptions.BadRequestException;
import com.example.users.exceptions.NotFoundException;
import com.example.users.global.variables.UserGlobalVariable;
import com.example.users.repositories.RefreshTokenJpa;
import com.example.users.repositories.UserJpa;
import com.example.users.sql.SQLQueryCountAndExists;
import com.example.users.utils.JWTUtils;
import com.example.users.utils.PasswordUtil;
import com.example.users.utils.RequestBodyParamsUtils;
import com.example.users.validators.create.AbstractCreatorValidator;
import com.example.users.validators.create.UserCreatorValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Object register(Map<String, Object> requestBodyParams, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        AbstractCreatorValidator<User> validator = new UserCreatorValidator(requestBodyParams, user, queryCountAndExists);
        if(!validator.isValid()){
            throw new BadRequestException("Validation error!:" + validator.getErrorMessages());
        }
        user.setPassword(PasswordUtil.getHashedPasswordSHA256(user.getPassword()));
        user.setEmailVerified(UserGlobalVariable.EmailNotVerified);
        user.setLastLogin(new Date());
        user.setCreatedTime(new Date());

        userJpa.save(user);

        return user.getMap();
    }
    @Override
    public Object login(Map<String, Object> requestBodyParams, HttpServletRequest request,  HttpServletResponse response){
        String usernameOrEmail = RequestBodyParamsUtils.getString(requestBodyParams, "usernameOrEmail", true, true);
        String password = RequestBodyParamsUtils.getString(requestBodyParams, "password", true, true);

        User user = getUserByUsernameOrEmail(usernameOrEmail);
        if(user == null)
            throw new NotFoundException();
        if(!PasswordUtil.check_password(user, password))
            throw new BadRequestException("input error: username or password is not correct!");
        RefreshToken refreshToken = new RefreshToken(user);
        refreshTokenJpa.save(refreshToken);
        String accessToken = JWTUtils.createJWTAccessToken(refreshToken);
        return Map.of(
            "accessToken", accessToken,
            "uuid", user.getUuid(),
            "refreshToken", refreshToken.getRefreshToken(),
            "user", user.getMap()
        );
    }

    @Override
    public Object uploadAccessToken(Map<String, Object> requestBodyParameters, HttpServletRequest request,  HttpServletResponse response){
        String refreshToken = RequestBodyParamsUtils.getString(requestBodyParameters, "refreshToken", true, true);

        RefreshToken refreshTokenModel = refreshTokenJpa.getRefreshTokenModelByRefreshToken(refreshToken);
        if(refreshTokenModel == null)
            throw new NotFoundException();
        String accessToken = JWTUtils.createJWTAccessToken(refreshTokenModel);
        return Map.of(
                "accessToken", accessToken
        );
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
