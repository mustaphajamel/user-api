package com.atos.userapi.dto;

import com.atos.userapi.entity.User;
import com.atos.userapi.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Class {@code UserResponseDto}
 */
@Data
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String country;
    private String phoneNumber;
    private Gender gender;

    public User toUserEntity() {
        return new User(id, name, birthDate, country, phoneNumber, gender);
    }
}
