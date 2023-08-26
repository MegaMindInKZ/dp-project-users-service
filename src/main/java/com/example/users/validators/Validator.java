package com.example.users.validators;

import com.example.users.entities.Table;

import java.util.List;
import java.util.Map;

public interface Validator<T extends Table>{
    boolean isValid();
    Map<String, List<String>> getErrorMessages();
}
