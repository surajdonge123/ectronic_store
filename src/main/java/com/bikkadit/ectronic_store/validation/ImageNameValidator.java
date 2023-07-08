package com.bikkadit.ectronic_store.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return false;
    }
}
