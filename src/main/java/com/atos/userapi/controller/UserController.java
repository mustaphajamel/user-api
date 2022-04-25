package com.atos.userapi.controller;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Aspect
@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("details")
    public UserResponseDto getUserDetails(@RequestParam Long id) {
        logger.info("Enter getUserDetails controller");
        return userService.getUserDetails(id);
    }

    @PostMapping("register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRequestDto user) {
        logger.info("Enter registerUser controller");
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }


}
