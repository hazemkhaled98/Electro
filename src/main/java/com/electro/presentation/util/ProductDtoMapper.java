package com.electro.presentation.util;

import com.electro.persistence.entities.Product;
import com.electro.presentation.dto.DisplayedProductDTO;
import com.electro.services.util.ImagesPathUtil;

import java.io.IOException;

public class ProductDtoMapper {

    private ProductDtoMapper(){
    }

    public static DisplayedProductDTO productToDto(Product product) throws IOException {
        return DisplayedProductDTO.builder()
                .name(product.getProductName())
                .category(product.getCategory())
                .price(product.getProductPrice())
                .description(product.getProductDescription())
                .mimeType(ImagesPathUtil.getMimeType(product.getProductPic()))
                .productPic(ImagesPathUtil.encodeFileToBase64(product.getProductPic()))
                .build();
    }
}
