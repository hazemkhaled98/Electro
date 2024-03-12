package com.electro;

import com.electro.persistence.entities.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("electro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Customer customer = new Customer();

        customer.setCustomerName("moataz");
        customer.setBirthday(LocalDate.of(2022, 3, 10));
        customer.setPassword("123456789");
        customer.setJob("software engineer");
        customer.setEmail("moataz@gmail.com");
        customer.setCity("Mansoura");
        customer.setCountry("Egypt");
        customer.setStreetNo("3");
        customer.setStreetName("elaswa");

        try {
            transaction.begin();

            entityManager.persist(customer);


            transaction.commit();

            System.out.println("Entities persisted successfully.");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}

