package com.example.users.validators.client;

import com.example.users.entities.Table;
import com.example.users.entities.UserModel;
import com.example.users.sql.SQLQueryCountAndExists;
import com.example.users.validators.AbstractValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUserModelRegistrationValidator extends AbstractValidator {
    private SQLQueryCountAndExists sqlQueryCountAndExists;
    public ClientUserModelRegistrationValidator(Map inputParameters, UserModel model, SQLQueryCountAndExists sqlQueryCountAndExists) throws ClassNotFoundException, NoSuchMethodException {
        super(inputParameters, model);
        this.sqlQueryCountAndExists = sqlQueryCountAndExists;

    }
    @Override
    protected void check() {
        UserModel user = (UserModel)getModel();
        validPassword(user);
        validUsername(user);
        validEmail(user);
    }

    private void validUsername(UserModel user){
        List<String> errors = new ArrayList<>();
        if(user.getUsername() == null)
            errors.add("username cannot be null");
        if(user.getUsername().isEmpty())
            errors.add("username length cannot be 0");
        if(sqlQueryCountAndExists.tableName("user_model").where("username = ?", user.getUsername()).exists())
            errors.add("username has been taken by another user");
        if(errors.isEmpty()) return;
        getErrorMessages().put("username", errors);
    }

    private void validEmail(UserModel userModel){
        List<String> errors = new ArrayList<>();
        if(userModel.getEmail() == null)
            errors.add("email cannot be null");
        if(userModel.getEmail().isEmpty())
            errors.add("email length cannot be 0");
        if(sqlQueryCountAndExists.tableName("user_model").where("email = ?", userModel.getEmail()).exists())
            errors.add("email has been taken by another user");
        if(errors.isEmpty()) return;
        getErrorMessages().put("email", errors);
    }
    private void validPassword(UserModel user){
        List<String> errors = new ArrayList<>();
        if(user.getPassword() == null)
            errors.add("password cannot be null");
        if(user.getPassword() != null && user.getPassword().length() < 8)
            errors.add("password length should be more than 8");
        if(errors.isEmpty()) return;
        getErrorMessages().put("password", errors);
    }
}
