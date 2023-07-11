package com.bikkadit.ectronic_store.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {


    public Logger logger=LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("message from isValid {}"+value);
        //Logic
        if (value.isBlank()){
            return  false;
        }else{
            return true;
        }

    }
}
