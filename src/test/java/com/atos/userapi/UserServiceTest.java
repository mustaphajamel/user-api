package com.atos.userapi;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.entity.User;
import com.atos.userapi.enums.Gender;
import com.atos.userapi.exception.UserNotFoundException;
import com.atos.userapi.repository.UserRepository;
import com.atos.userapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void should_return_user_details_when_existing() throws UserNotFoundException, ParseException {
        //GIVEN
        long userId = 1L;
        User user = new User(
                userId,
                "aName",
                new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-24"),
                "FRANCE",
                "22222222",
                Gender.MALE);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(userId)).thenReturn(userOptional);

        //WHEN
        UserResponseDto userDetails = userService.getUserDetails(userId);

        //THEN
        assertNotNull(userDetails);
        assertEquals(userId, userDetails.getId());
    }

    @Test
    void a_valid_user_should_be_registered() throws ParseException {
        //GIVEN
        UserRequestDto userRequestDto = new UserRequestDto("aName",
                new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-24"),
                "FRANCE",
                "22222222",
                Gender.MALE);
        User user = userRequestDto.toUserEntity();
        User savedUser = new User(
                1L,
                "aName",
                new SimpleDateFormat("yyyy-MM-dd").parse("2000-04-24"),
                "FRANCE",
                "22222222",
                Gender.MALE);
        when(userRepository.save(user)).thenReturn(savedUser);

        //WHEN
        UserResponseDto userResponseDto = userService.registerUser(userRequestDto);

        //THEN
        assertNotNull(userResponseDto);
        assertNotNull(userResponseDto.getId());
        assertNotEquals(0, userResponseDto.getId());
        assertEquals("aName", userResponseDto.getName());
    }
}