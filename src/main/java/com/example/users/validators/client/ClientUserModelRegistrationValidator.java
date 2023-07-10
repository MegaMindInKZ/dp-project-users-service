package com.example.users.validators.client;

import com.example.users.entities.User;
import com.example.users.sql.SQLQueryCountAndExists;
import com.example.users.validators.AbstractValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUserModelRegistrationValidator extends AbstractValidator {
    private SQLQueryCountAndExists sqlQueryCountAndExists;
    public ClientUserModelRegistrationValidator(Map inputParameters, User model, SQLQueryCountAndExists sqlQueryCountAndExists) throws ClassNotFoundException, NoSuchMethodException {
        super(inputParameters, model);
        this.sqlQueryCountAndExists = sqlQueryCountAndExists;

    }
    @Override
    protected void check() {
        User user = (User)getModel();
        validPassword(user);
        validUsername(user);
        validEmail(user);
    }

    private void validUsername(User user){
        List<String> errors = new ArrayList<>();
        if(user.getUsername() == null)
            errors.add("username cannot be null");
        if(user.getUsername().isEmpty())
            errors.add("username length cannot be 0");
        if(sqlQueryCountAndExists.tableName("user").where("username = ?", user.getUsername()).exists())
            errors.add("username has been taken by another user");
        if(errors.isEmpty()) return;
        getErrorMessages().put("username", errors);
    }

    private void validEmail(User user){
        List<String> errors = new ArrayList<>();
        if(user.getEmail() == null)
            errors.add("email cannot be null");
        if(user.getEmail().isEmpty())
            errors.add("email length cannot be 0");
        if(sqlQueryCountAndExists.tableName("user").where("email = ?", user.getEmail()).exists())
            errors.add("email has been taken by another user");
        if(errors.isEmpty()) return;
        getErrorMessages().put("email", errors);
    }
    private void validPassword(User user){
        List<String> errors = new ArrayList<>();
        if(user.getPassword() == null)
            errors.add("password cannot be null");
        if(user.getPassword() != null && user.getPassword().length() < 8)
            errors.add("password length should be more than 8");
        if(errors.isEmpty()) return;
        getErrorMessages().put("password", errors);
    }
}
