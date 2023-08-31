package com.example.users.utils.validators;

import com.example.users.data.entities.Table;

import java.util.Map;

public class NoValidate<T extends Table> extends AbstractValidator {

    public NoValidate() {
    }

    public NoValidate(Map inputParameters, Table model) throws ClassNotFoundException, NoSuchMethodException {
        super(inputParameters, model);
    }

    @Override
    protected void check() {

    }
}
