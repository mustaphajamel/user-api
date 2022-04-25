package com.atos.userapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AdultAgeValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface ValidAdultAge {
    String message() default "Only adult person can register";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}