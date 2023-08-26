package com.example.users.validators;

import com.example.users.entities.Table;
import com.example.users.validators.create.AbstractCreatorValidator;

import java.util.Map;

public class NoValidate<T extends Table> extends AbstractCreatorValidator {

    public NoValidate() {
    }

    public NoValidate(Map inputParameters, Table model) throws ClassNotFoundException, NoSuchMethodException {
        super(inputParameters, model);
    }

    @Override
    protected void check() {

    }
}
