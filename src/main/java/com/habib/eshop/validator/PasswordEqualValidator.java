package com.habib.eshop.validator;

import com.habib.eshop.ennotation.PasswordEqual;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class PasswordEqualValidator implements ConstraintValidator<PasswordEqual, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    public void initialize(PasswordEqual constraint){
        firstFieldName = constraint.first();
        secondFieldName = constraint.second();
        message = constraint.message();
    }
    public boolean isValid(Object value, ConstraintValidatorContext context){
        boolean valid = true;
        try {
            final Object firstObj = getValue(value, firstFieldName);
            final Object secondObj = getValue(value, secondFieldName);

            valid = Objects.equals(firstObj, secondObj);
        } catch (final Exception ignore){
            //ignor
        }
    }
    if(!valid){
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(firstFieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
    return valid;
    private Object getValue(Objects objects, String fieldName) throws NoSuchFieldException, IllegalAccessException{
        Field field = objects.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(object);
    }
}
