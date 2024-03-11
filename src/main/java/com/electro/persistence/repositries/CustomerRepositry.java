package com.electro.persistence.repositries;

import com.electro.persistence.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class CustomerRepositry extends Repositry<Customer> {


    public CustomerRepositry(EntityManager entityManager) {
        super(entityManager);
        super.setType(Customer.class);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        Customer Customer = null;
        email = email.toLowerCase();
        try {
            TypedQuery<Customer> query = entityManager.createQuery( "SELECT c FROM Customer c WHERE c.email = :email", Customer.class );
            query.setParameter( "email", email );
            Customer = query.getSingleResult();
        } catch (NoResultException e) {
            System.err.printf("Customer with email %s was not found%n", email);
        }
        return Optional.ofNullable( Customer );
    }
}
