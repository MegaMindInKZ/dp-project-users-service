package com.example.users.utils.validators;

import com.example.users.data.entities.Table;
import com.example.users.data.entities.User;
import com.example.users.data.repositories.UserJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserValidator<T extends Table> extends AbstractValidator {
    private UserJpa userJpa;
    public UserValidator(Map inputParameters, User model, UserJpa userJpa, String... parsingFieldNames){
        super(inputParameters, model, parsingFieldNames);
        this.userJpa = userJpa;

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
        else{
            if (user.getUsername().isEmpty())
                errors.add("username length cannot be 0");
            if (userJpa.getUserModelByUsername(user.getUsername()) != null)
                errors.add("username has been taken by another user");
        }

        if(errors.isEmpty()) return;
        getErrorMessages().put("username", errors);
    }

    private void validEmail(User user){
        List<String> errors = new ArrayList<>();
        if(user.getEmail() == null)
            errors.add("email cannot be null");
        else {
            if (user.getEmail().isEmpty())
                errors.add("email length cannot be 0");
            if (userJpa.getUserModelByEmail(user.getEmail()) != null)
                errors.add("email has been taken by another user");
        }

        if(errors.isEmpty()) return;
        getErrorMessages().put("email", errors);
    }
    private void validPassword(User user){
        List<String> errors = new ArrayList<>();
        if(user.getPassword() == null)
            errors.add("password cannot be null");
        else{
            if (user.getPassword() != null && user.getPassword().length() < 8)
                errors.add("password length should be more than 8");
        }

        if (errors.isEmpty()) return;
        getErrorMessages().put("password", errors);
    }
}
