package com.atos.userapi.dto;

import com.atos.userapi.entity.User;
import com.atos.userapi.enums.Gender;
import com.atos.userapi.validation.ValidAdultAge;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Class {@code UserRequestDto}
 */
@Data
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Birthday date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ValidAdultAge
    @Past
    private Date birthDate;
    @NotBlank(message = "Country is mandatory")
    @Pattern(regexp = "^$|^[Ff][Rr][Aa][Nn][Cc][Ee]$", message = "You should be French to register")
    private String country;
    @Pattern(regexp = "\\d+", message = "The phone number should contain only digits")
    private String phoneNumber;
    private Gender gender;

    public User toUserEntity() {
        return new User(name, birthDate, country, phoneNumber, gender);
    }

}
