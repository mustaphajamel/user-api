package com.atos.userapi.exception;

/**
 * Class {@code UserNotFoundException}
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
