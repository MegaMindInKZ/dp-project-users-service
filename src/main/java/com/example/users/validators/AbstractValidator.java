package com.example.users.validators;

import com.example.users.annotations.Ignore;
import com.example.users.entities.Table;
import com.example.users.exceptions.BadRequestException;
import com.example.users.exceptions.ServiceException;
import com.example.users.utils.RequestBodyParamsUtils;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractValidator<T extends Table> {
    private List<String> ignoringFieldsName = new ArrayList<>();
    private Map<String, List<String>> errorMessages = new HashMap<>();
    private Map<String, Object> inputParameters;
    private T model;
    protected AbstractValidator(){}

    protected AbstractValidator(Map<String, Object> inputParameters, T model){
        this.model = model;
        this.inputParameters = inputParameters;
    }
    public boolean isValid(){
        parseModel();
        check();
        return errorMessages.size() == 0;
    }

    private void parseModel(){
        for(Map.Entry<String, Object> entry: inputParameters.entrySet()){
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            Field field;
            try {
                field = model.getClass().getDeclaredField(fieldName);
            }catch (Exception ex){
                continue;
            }
            if(field.isAnnotationPresent(Ignore.class))
                continue;
            if (!RequestBodyParamsUtils.isInstance(value, field, model))
                throw new BadRequestException("mismatched types " + value.getClass().getTypeName() + " and " + field.getType().getName());

            try {
                model.setValueByFieldName(fieldName, value);
            } catch (NoSuchMethodException e) {
                throw new ServiceException(e);
            } catch (InvocationTargetException e) {
                throw new BadRequestException("mismatched types " + value.getClass().getTypeName() + " and " + field.getType().getName());
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }

    }


    protected abstract void check();
}
