package com.example.users.validators.update;

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
public abstract class AbstractUpdateValidator<T extends Table> implements Validator {
    private List<String> ignoringFieldsName = new ArrayList<>();
    private Map<String, List<String>> errorMessages = new HashMap<>();
    private Map<String, Object> inputParameters;
    private boolean isChecked = false;
    private T model;
    private List<String> updatingFieldNames;

    protected AbstractUpdateValidator(){}

    protected AbstractUpdateValidator(Map<String, Object> inputParameters, T model, List<String> updatingFieldNames){
        this.model = model;
        this.inputParameters = inputParameters;
        this.updatingFieldNames = updatingFieldNames;
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
        for(String updatingFieldName: updatingFieldNames){
            Object value = inputParameters.get(updatingFieldName);

            Field field;
            try {
                field = model.getClass().getDeclaredField(updatingFieldName);
            }catch (Exception ex){
                throw new ServiceException(ex);
            }

            if (!RequestBodyParamsUtils.isInstance(value, field, model))
                throw new BadRequestException("mismatched types " + value.getClass().getTypeName() + " and " + field.getType().getName());

            try {
                model.setValueByFieldName(updatingFieldName, value);
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
