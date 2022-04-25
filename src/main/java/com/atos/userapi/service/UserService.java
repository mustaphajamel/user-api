package com.atos.userapi.service;

import com.atos.userapi.configuration.SLF4J;
import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.repository.UserRepository;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(SLF4J.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto getUserDetails(Long id) {
        logger.info("start getting user derails. userId ="+id);
        return userRepository.findById(id).map(User::toUserResponseDto).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ));
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        logger.info("start registering a user ");
        User userSaved = userRepository.save(userRequestDto.toUserEntity());
        return userSaved.toUserResponseDto();
    }
}
