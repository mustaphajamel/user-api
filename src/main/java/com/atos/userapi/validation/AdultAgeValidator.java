package com.atos.userapi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Class {@code AdultAgeValidator}
 */
public class AdultAgeValidator implements ConstraintValidator<ValidAdultAge, Date> {

    public static final int ADULT_AGE = 18;

    /**
     * @param birthDate - birthday date of the user
     * @param context - Validator context
     * @return boolean
     */
    @Override
    public boolean isValid(Date birthDate, ConstraintValidatorContext context) {

        return birthDate != null && ChronoUnit.YEARS.between(
                birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()) > ADULT_AGE;
    }
}