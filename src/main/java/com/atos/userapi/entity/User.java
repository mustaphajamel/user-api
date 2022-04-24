package com.atos.userapi.entity;

import com.atos.userapi.dto.UserRequestDto;
import com.atos.userapi.enums.Gender;
import com.atos.userapi.dto.UserResponseDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date birthDate;
    private String country;
    private String phoneNumber;
    private Gender gender;

    public User() {

    }

    public User(String name, Date birthDate, String country, String phoneNumber, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public UserRequestDto toUserRequestDto() {
        return new UserRequestDto(this.name, this.birthDate, this.country, this.phoneNumber, this.gender);
    }

    public UserResponseDto toUserResponseDto() {
        return new UserResponseDto(this.id, this.name, this.birthDate, this.country, this.phoneNumber, this.gender);
    }
}
