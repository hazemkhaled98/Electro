package com.electro.presentation.dto;

import com.electro.persistence.entities.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@ToString
public class DisplayedProductDTO {

    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private String category;
    private String productPic;


    public static List<DisplayedProductDTO> of(List<Product> products) {
            return products.stream()
                    .map(DisplayedProductDTO::of)
                    .toList();
    }

    public static DisplayedProductDTO of(Product product){
        return DisplayedProductDTO.builder()
                .name(product.getProductName())
                .description(product.getProductDescription())
                .quantity(product.getStockQuantity())
                .price(product.getProductPrice())
                .category(product.getCategory())
                .productPic(product.getProductPic())
                .productPic(product.getProductPic())
                .build();
    }
}
