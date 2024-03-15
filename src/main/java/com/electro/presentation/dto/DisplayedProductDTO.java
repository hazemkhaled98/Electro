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
public class DisplayedProductDTO {

    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private String category;
    private String productPic;
    private String mimeType;
}
