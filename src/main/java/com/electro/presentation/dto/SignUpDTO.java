package com.electro.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;


@Builder
@Getter
@ToString
public class SignUpDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private LocalDate birthdate;
    private String job;
    private String country;
    private String city;
    private String streetNo;
    private String streetName;
    private String creditLimit;
}
