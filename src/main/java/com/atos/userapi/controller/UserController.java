package com.atos.userapi.controller;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Class {@code UserController}
 */
@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param id - ID of the user to get
     * @return UserResponseDto
     */
    @ApiOperation(value = "get user details", notes = "use id user ( Long type )", nickname = "getUserDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User details in json format"),
            @ApiResponse(code = 404, message = "User not found")})
    @GetMapping("/{id}")
    public UserResponseDto getUserDetails(@PathVariable Long id) throws UserNotFoundException {
        logger.info("Enter getUserDetails controller");
        return userService.getUserDetails(id);
    }

    /**
     * @param user - User to add
     * @return ResponseEntity<UserResponseDto>
     */
    @ApiOperation(value = "register a french user", notes = "User request body as a parameter", nickname = "registerUser")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Json format of the user added"),
            @ApiResponse(code = 400, message = "Bad request please check response message")})
    @PostMapping("")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRequestDto user) {
        logger.info("Enter registerUser controller");
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
}