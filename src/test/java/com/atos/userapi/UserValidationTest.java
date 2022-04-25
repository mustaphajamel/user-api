package com.atos.userapi;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.enums.Gender;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidationTest {

    @Test
    public void a_french_user_should_be_valid() throws ParseException {


        //GIVEN
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = df.parse("2000-04-24");

        UserRequestDto userRequestDto = new UserRequestDto("aName",aDate, "FRANCE", "22222222", Gender.MALE);

        //WHEN
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate( userRequestDto );

        //THEN
        assertTrue(violations.isEmpty());
    }

    @Test
    public void a_non_french_user_should_not_be_valid() throws ParseException {


        //GIVEN
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = df.parse("2000-04-24");

        UserRequestDto userRequestDto = new UserRequestDto("aName",aDate, "GERMANY", "22222222", Gender.MALE);

        //WHEN
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate( userRequestDto );

        //THEN
        assertEquals(1, violations.size());
        List<String> violationMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertTrue( violationMessages.contains("You should be French to register"));
    }

    @Test
    public void a_french_user_should_be_valid_regardless_case_sensitivity() throws ParseException {


        //GIVEN
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = df.parse("2000-04-24");

        UserRequestDto userRequestDto = new UserRequestDto("aName",aDate, "FrANcE", "22222222", Gender.MALE);

        //WHEN
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate( userRequestDto );

        //THEN
        assertTrue(violations.isEmpty());
    }

    @Test
    public void only_adult_user_should_be_valid() throws ParseException {


        //GIVEN
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = df.parse("2010-04-24");

        UserRequestDto userRequestDto = new UserRequestDto("aName",aDate, "FrANcE", "22222222", Gender.MALE);

        //WHEN
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate( userRequestDto );

        //THEN
        List<String> violationMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertTrue( violationMessages.contains("Only adult person can register"));
    }

    @Test
    public void name_and_birthday_date_should_be_mandatory() throws ParseException {


        //GIVEN
        String anEmptyName = " ";
        UserRequestDto userRequestDto = new UserRequestDto(anEmptyName,null, "FrANcE", "22222222", Gender.MALE);

        //WHEN
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate( userRequestDto );

        //THEN
        List<String> violationMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertTrue( violationMessages.contains("Name is mandatory"));
        assertTrue( violationMessages.contains("Birthday date is mandatory"));

    }
}