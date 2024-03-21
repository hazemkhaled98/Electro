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

public class CartItemDTO {

    private CartItemProductDTO itemProductDTO;
    private Integer quantity;

}
