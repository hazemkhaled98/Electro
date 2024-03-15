package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private ProductService() {
    }

    public static long getPagesCount(){
        return Database.doInTransaction(em -> {
            ProductRepository productRepositry = new ProductRepository(em);
            return productRepositry.getPagesCount();
        });
    }

    public static List<Product> getPageOfProduct(String page){
        try {
            int pageNumber = Integer.parseInt(page);
            return Database.doInTransaction(em -> {
                ProductRepository productRepositry = new ProductRepository(em);
                return productRepositry.getPageOfProduct(pageNumber);
            });
        } catch (Exception e) {
           return new ArrayList<>();
        }
    }

    public static void deleteProduct(String productId) {
        try {
            int id = Integer.parseInt(productId);
            Database.doInTransactionWithoutResult(em -> {
                ProductRepository productRepositry = new ProductRepository(em);
                productRepositry.deleteById(id);
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Optional<Product> getProductById(String productId) {
        try {
            int id = Integer.parseInt(productId);
            return Database.doInTransaction(em -> {
                ProductRepository productRepositry = new ProductRepository(em);
                return productRepositry.get(id);
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
