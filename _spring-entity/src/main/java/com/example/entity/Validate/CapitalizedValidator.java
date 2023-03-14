package com.example.entity.Validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapitalizedValidator implements ConstraintValidator<ValidateUsername, String> {
    @Override
    public void initialize(ValidateUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
