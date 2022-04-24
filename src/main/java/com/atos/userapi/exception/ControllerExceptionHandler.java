package com.atos.userapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    public static final String VALIDATION_ERROR_SEPERATOR = ":";
    public static final String REQUEST_VALIDATION_FAIL = "REQUEST_VALIDATION_FAIL";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ApiError(HttpStatus.BAD_REQUEST, REQUEST_VALIDATION_FAIL,
                e.getBindingResult().getFieldErrors().stream()
                .map(filedError -> String.format("%s %s %s", filedError.getField(), VALIDATION_ERROR_SEPERATOR, filedError
                .getDefaultMessage())).collect(Collectors.toList()));
    }
}