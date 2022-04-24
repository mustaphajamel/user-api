package com.atos.userapi.service;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.repository.UserRepository;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto getUserDetails(Long id) {
        return userRepository.findById(id).map(User::toUserResponseDto).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ));
    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        User userSaved = userRepository.save(userRequestDto.toUserEntity());
        return userSaved.toUserResponseDto();
    }
}
