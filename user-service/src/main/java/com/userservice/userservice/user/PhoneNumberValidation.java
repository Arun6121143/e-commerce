package com.userservice.userservice.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserPhoneNumberValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberValidation {
    String message() default "Invalid phone number or user with same number already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
