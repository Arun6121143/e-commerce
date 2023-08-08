package com.userservice.userservice.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UserPhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation,String> {

    @Override
    public void initialize(PhoneNumberValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phonenumber, ConstraintValidatorContext constraintValidatorContext) {
        if(phonenumber==null || phonenumber.isEmpty() || phonenumber.length()!=10){
            return false;
        }
        return true;
    }
}
