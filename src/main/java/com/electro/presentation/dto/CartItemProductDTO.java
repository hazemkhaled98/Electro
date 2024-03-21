package com.electro.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@ToString
public class CartItemProductDTO {

    private Integer id;
    private String productName;
    private Integer stockQuantity;
    private String productDescription;
    private String productPic;
    private BigDecimal productPrice;
    private String category;
}
