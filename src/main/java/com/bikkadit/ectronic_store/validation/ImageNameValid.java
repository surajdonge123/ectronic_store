package com.bikkadit.ectronic_store.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface  ImageNameValid {
    //Error message
    String message() default "Invalid image name !!";

    //Represent Group of elements
    Class<?>[] groups() default { };

    //additional information about annotations
    Class<? extends Payload>[] payload() default { };



}
