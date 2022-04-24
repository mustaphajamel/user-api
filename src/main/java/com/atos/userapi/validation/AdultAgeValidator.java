package com.atos.userapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AdultAgeValidator implements ConstraintValidator<ValidAdultAge, Date> {

    public static final int ADULT_AGE = 18;

    @Override
    public boolean isValid(Date birthDate, ConstraintValidatorContext context) {
        return ChronoUnit.YEARS.between(
                birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()) > ADULT_AGE;
    }
}