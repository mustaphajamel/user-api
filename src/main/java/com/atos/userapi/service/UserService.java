package com.atos.userapi.service;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.entity.User;
import com.atos.userapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Class {@code UserService}
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param id - ID of the user to get
     * @return UserResponseDto
     */
    public UserResponseDto getUserDetails(Long id) {
        logger.info("start getting user derails where userId =" + id);
        return userRepository.findById(id).map(User::toUserResponseDto).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
        ));
    }

    /**
     * @param userRequestDto - User details to register
     * @return UserResponseDto
     */
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        logger.info("start registering a user");
        User userSaved = userRepository.save(userRequestDto.toUserEntity());
        return userSaved.toUserResponseDto();
    }
}
