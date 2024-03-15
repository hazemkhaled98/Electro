package com.electro.persistence.repositries;


import com.electro.persistence.entities.Product;
import jakarta.persistence.EntityManager;

public class ProductRepositry extends Repository<Product>{
    public ProductRepositry(EntityManager entityManager) {
        super(entityManager);
        super.setType(Product.class);
    }
}
