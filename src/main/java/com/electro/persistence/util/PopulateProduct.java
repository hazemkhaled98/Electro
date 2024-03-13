package com.electro.persistence.util;

import com.electro.persistence.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class PopulateProduct {
    public static void main(String[] args) {
        // Populate the database with products
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("electro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Product product = new Product();
        product.setProductName("macbook");
        product.setProductDescription("A laptop with 8GB RAM, 512GB SSD, and 15.6-inch display");
        product.setProductPrice(BigDecimal.valueOf(1000.00));
        product.setStockQuantity(100);
        product.setCategory("laptop");
        entityManager.persist(product);
        product = new Product();
        product.setProductName("lenovo");
        product.setProductDescription("A laptop with 8GB RAM, 512GB SSD, and 15.6-inch display");
        product.setProductPrice(BigDecimal.valueOf(1000.00));
        product.setStockQuantity(100);
        product.setCategory("laptop");
        entityManager.persist(product);

        product = new Product();
        product.setProductName("iphone");
        product.setProductDescription("A smartphone with 6GB RAM, 128GB storage, and 6.5-inch display");
        product.setProductPrice(BigDecimal.valueOf(500.00));
        product.setStockQuantity(200);
        product.setCategory("smartphone");
        entityManager.persist(product);

        product = new Product();
        product.setProductName("samsung");
        product.setProductDescription("A smartphone with 6GB RAM, 128GB storage, and 6.5-inch display");
        product.setProductPrice(BigDecimal.valueOf(500.00));
        product.setStockQuantity(200);
        product.setCategory("smartphone");
        entityManager.persist(product);

        product = new Product();
        product.setProductName("kindle");
        product.setProductDescription("A tablet with 4GB RAM, 64GB storage, and 10-inch display");
        product.setProductPrice(BigDecimal.valueOf(300.00));
        product.setStockQuantity(150);
        product.setCategory("tablet");
        entityManager.persist(product);

        product = new Product();
        product.setProductName("Ipad");
        product.setProductDescription("A tablet with 4GB RAM, 64GB storage, and 10-inch display");
        product.setProductPrice(BigDecimal.valueOf(300.00));
        product.setStockQuantity(150);
        product.setCategory("tablet");
        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
