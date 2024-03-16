package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.ProductRepository;
import com.electro.presentation.dto.CreatedProductDTO;
import com.electro.services.enums.FileType;
import com.electro.services.util.ImagesPathUtil;
import com.electro.presentation.dto.CreatedProductDTO;
import com.electro.services.enums.FileType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductService {

    private ProductService() {
    }

    public static long getPagesCount() {
        return Database.doInTransaction(em -> {
            ProductRepository productRepository = new ProductRepository(em);
            return productRepository.getPagesCount();
        });
    }

    public static List<Product> getPageOfProduct(String page) {
        try {
            int pageNumber = Integer.parseInt(page);
            return Database.doInTransaction(em -> {
                ProductRepository productRepository = new ProductRepository(em);
                return productRepository.getPageOfProduct(pageNumber);
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static boolean deleteProduct(String productId) {
        try {
            int id = Integer.parseInt(productId);
            Database.doInTransactionWithoutResult(em -> {
                ProductRepository productRepository = new ProductRepository(em);
                productRepository.deleteById(id);
            });
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static Optional<Product> getProductById(String productId) {
        try {
            int id = Integer.parseInt(productId);
            return Database.doInTransaction(em -> {
                ProductRepository productRepository = new ProductRepository(em);
                return productRepository.get(id);
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<Product> updateProduct(Product product) {
        try {
            return Database.doInTransaction(em -> {
                ProductRepository productRepository = new ProductRepository(em);
                return Optional.of(productRepository.update(product));
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }


    public static Optional<Product> addProduct(CreatedProductDTO createdProductDTO) {
        return Database.doInTransaction(em -> {
            try {
                // store product in database
                ProductRepository productRepository = new ProductRepository(em);
                Product newProduct = new Product();
                newProduct.setProductName(createdProductDTO.getName());
                newProduct.setProductDescription(createdProductDTO.getDescription());
                newProduct.setStockQuantity(createdProductDTO.getQuantity());
                newProduct.setProductPrice(createdProductDTO.getPrice());
                newProduct.setCategory(createdProductDTO.getCategory());
                productRepository.create(newProduct);

                // store the product picture path to database
                String productPicPath;
                try {
                    productPicPath = ImagesPathUtil.storeFileFromPart(createdProductDTO.getFilePart(),
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

    public static List<Product> getAllProducts() {
        return Database.doInTransaction(em -> {
            ProductRepository productRepository = new ProductRepository(em);
            return productRepository.getAll();
        });
    }

    public static List<Product> getProductsByCategory(String category) {
        return Database.doInTransaction(em -> {
            ProductRepository productRepository = new ProductRepository(em);
            return productRepository.getByCategory(category);
        });
    }
}



