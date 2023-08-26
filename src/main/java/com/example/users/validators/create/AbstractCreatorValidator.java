package com.example.users.validators.create;

import com.example.users.annotations.Ignore;
import com.example.users.entities.Table;
import com.example.users.exceptions.BadRequestException;
import com.example.users.exceptions.ServiceException;
import com.example.users.utils.RequestBodyParamsUtils;
import com.example.users.validators.Validator;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractCreatorValidator<T extends Table> implements Validator {
    private List<String> ignoringFieldsName = new ArrayList<>();
    private Map<String, List<String>> errorMessages = new HashMap<>();
    private Map<String, Object> inputParameters;
    private boolean isChecked = false;
    private T model;
    protected AbstractCreatorValidator(){}

    protected AbstractCreatorValidator(Map<String, Object> inputParameters, T model){
        this.model = model;
        this.inputParameters = inputParameters;
    }
    public boolean isValid(){
        if(isChecked){
            return errorMessages.size() == 0;
        }
        parseModel();
        check();
        isChecked = true;
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
