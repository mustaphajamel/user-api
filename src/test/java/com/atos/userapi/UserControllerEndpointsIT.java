package com.atos.userapi;


import com.atos.userapi.configuration.ApplicationConfiguration;
import com.atos.userapi.controller.UserController;
import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.dto.UserResponseDto;
import com.atos.userapi.entity.User;
import com.atos.userapi.enums.Gender;
import com.atos.userapi.repository.UserRepository;
import com.atos.userapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(ApplicationConfiguration.class)
public class UserControllerEndpointsIT {

    @SpyBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_a_valid_user() throws Exception {
        //GIVEN
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = df.parse("2000-04-24");

        UserRequestDto userRequestDto = new UserRequestDto("aName", aDate, "FRANCE", "20000", Gender.MALE);
        UserResponseDto userResponseDto = new UserResponseDto(1L, "aName", aDate, "FRANCE", "20000", Gender.MALE);
        when(userRepository.save(userRequestDto.toUserEntity())).thenReturn(userResponseDto.toUserEntity());

        String userRequestBody = "{\n" +
                "    \"name\": \"aName\",\n" +
                "    \"birthDate\": \"" + df.format(aDate) + "\",\n" +
                "    \"country\": \"FRANCE\",\n" +
                "    \"phoneNumber\": \"20000\",\n" +
                "    \"gender\": \"MALE\"\n" +
                "}";

        //WHEN
        ResultActions result = mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userRequestBody));

        //THEN
        MvcResult mvcResult = result.andExpect(status().isCreated()).andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(userResponseDto), actualResponseBody);
    }

    @SneakyThrows
    @Test
    void get_an_existing_user() {
        //GIVEN
        long userId = 1L;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = df.parse("2022-04-24");

        User expectedUser = new User(userId, "aName",
                aDate, "FRANCE", "20000", Gender.MALE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        //WHEN
        ResultActions resultGet = mockMvc
                .perform(get("/users/{id}",String.valueOf(userId)));

        //THEN
        MockHttpServletResponse response = resultGet.andExpect(status().isOk())
                .andReturn().getResponse();
        String responseContentAsString = response.getContentAsString();
        assertEquals(objectMapper.writeValueAsString(expectedUser.toUserResponseDto()), responseContentAsString);
    }

    @SneakyThrows
    @Test
    public void get_a_non_existing_user() {
        //GIVEN
        long userId = 1L;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date aDate = df.parse("2022-04-24");


        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //WHEN
        ResultActions resultGet = mockMvc
                .perform(get("/users/{id}",String.valueOf(userId)));

        //THEN
        resultGet.andExpect(status().isNotFound());
    }
}
