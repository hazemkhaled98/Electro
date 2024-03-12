package com.electro.presentation.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
public class LoginDTO {
    private String email;
    private String password;

}
