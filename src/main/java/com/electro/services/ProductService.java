package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.CustomerRepositry;
import com.electro.persistence.repositries.ProductRepositry;
import com.electro.presentation.dto.CreatedProductDTO;
import com.electro.services.enums.FileType;
import com.electro.services.util.FileSystemUtil;

import java.io.IOException;
import java.util.Optional;

public class ProductService {
    public static Optional<Product> addProduct(CreatedProductDTO createdProductDTO){
        return Database.doInTransaction(em -> {
            try {
                // store product in database
                ProductRepositry productRepositry = new ProductRepositry(em);
                Product newProduct = new Product();
                newProduct.setProductName(createdProductDTO.getName());
                newProduct.setProductDescription(createdProductDTO.getDescription());
                newProduct.setStockQuantity(createdProductDTO.getQuantity());
                newProduct.setProductPrice(createdProductDTO.getPrice());
                newProduct.setCategory(createdProductDTO.getCategory());
                productRepositry.create(newProduct);

                // store the product picture path to database
                String productPicPath;
                try {
                    productPicPath = FileSystemUtil.storeFileFromPart(createdProductDTO.getFilePart(),
                            String.valueOf(newProduct.getId()), FileType.PRODUCT_PIC);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                newProduct.setProductPic(productPicPath);

                return Optional.of(newProduct);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return Optional.empty();
            }
        });

    }

    public static Optional<Product> getProduct(int productId){
        return Database.doInTransaction(em -> {
            try {
                ProductRepositry productRepositry = new ProductRepositry(em);

                return productRepositry.get(productId);

            } catch (Exception e) {
                System.err.println(e.getMessage());
                return Optional.empty();
            }
        });
    }
}
