package com.example.users.validators;

import com.example.users.annotations.Ignore;
import com.example.users.entities.Table;
import com.example.users.exceptions.BadRequestException;
import com.example.users.exceptions.ServiceException;
import com.example.users.utils.RequestBodyParamsUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public abstract class AbstractValidator<T extends Table> {
    protected Map<String, List<String>> errorMessages = new HashMap<>();
    protected Map<String, Object> inputParameters;
    protected boolean isChecked = false;
    protected T model;
    String[] parsingFieldNames;
    protected AbstractValidator(Map<String, Object> inputParameters, T model, String... parsingFieldName){
        this.model = model;
        this.inputParameters = inputParameters;
        this.parsingFieldNames = parsingFieldName;
    }
    protected abstract void check();
    public boolean isValid(){
        if(isChecked){
            return errorMessages.size() == 0;
        }
        parseModel();
        postCheck();
        check();
        preCheck();
        isChecked = true;
        return errorMessages.size() == 0;
    }
    protected void postCheck(){

    }
    protected void preCheck(){

    }
    private void parseModel(){
        for(String parsingFieldName: parsingFieldNames){
            String fieldName = parsingFieldName;
            Object value = inputParameters.get(fieldName);

            if(value == null)
                continue;

            Field field;
            try {
                field = model.getClass().getDeclaredField(fieldName);
            }catch (Exception ex){
                continue;
            }
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
    public Map<String, List<String>> getErrorMessages(){
        return errorMessages;
    }
}
