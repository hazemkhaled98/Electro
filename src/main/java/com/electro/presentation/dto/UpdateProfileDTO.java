package com.electro.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;


@Builder
@Getter
@ToString
public class UpdateProfileDTO {
    private String name;
    private String job;
    private String country;
    private String city;
    private String streetNo;
    private String streetName;
    private BigDecimal creditLimit;
}