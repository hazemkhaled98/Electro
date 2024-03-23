package com.electro.presentation.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AddToCartRespDTO {
    private String message;
    private int cartItemsCount;
}
