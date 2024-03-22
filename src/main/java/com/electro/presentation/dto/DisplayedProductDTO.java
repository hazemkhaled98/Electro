package com.electro.presentation.dto;

import com.electro.persistence.entities.Product;
import com.electro.services.util.ImagesPathUtil;
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
    private String mimeType;


    public static List<DisplayedProductDTO> of(List<Product> products) {
            return products.stream()
                    .map(DisplayedProductDTO::of)
                    .toList();
    }

    public static DisplayedProductDTO of(Product product){
        try {
            return DisplayedProductDTO.builder()
                    .name(product.getProductName())
                    .description(product.getProductDescription())
                    .quantity(product.getStockQuantity())
                    .price(product.getProductPrice())
                    .category(product.getCategory())
                    .productPic(product.getProductPic())
                    .productPic(ImagesPathUtil.encodeFileToBase64(product.getProductPic()))
                    .mimeType(ImagesPathUtil.getMimeType(product.getProductPic()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
