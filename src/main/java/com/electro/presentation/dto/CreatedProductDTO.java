package com.electro.presentation.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@ToString
public class CreatedProductDTO {
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private String category;
    private Part filePart;

}
