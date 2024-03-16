package com.electro.persistence.util;

import com.electro.persistence.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PopulateCustomers {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            // Populate the database with customers
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("electro");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Customer customer = new Customer();
            customer.setCustomerName("John Doe");
            customer.setBirthday(LocalDate.of(1990, 1, 1));
            customer.setPassword("password");
            customer.setJob("Software Engineer");
            customer.setEmail("asd" + i + "@asd.com");
            customer.setCreditLimit(BigDecimal.valueOf(1000));
            customer.setCity("Cairo");
            customer.setCountry("Egypt");
            customer.setStreetNo("1");
            customer.setStreetName("Elm");
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
