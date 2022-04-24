package com.atos.userapi.controller;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("details")
    public UserResponseDto getUserDetails(@RequestParam Long id) {
        return userService.getUserDetails(id);
    }

    @PostMapping("register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRequestDto user) {

        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }


}
