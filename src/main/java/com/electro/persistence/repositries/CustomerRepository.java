package com.electro.persistence.repositries;

import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class CustomerRepository extends Repository<Customer> {

    private final static int MAX_RESULTS = 5;

    public CustomerRepository(EntityManager entityManager) {
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
        return Optional.ofNullable(Customer);
    }
    public List<Customer> getPageOfCustomer(int pageNumber) {
        TypedQuery<Customer> query = entityManager.createQuery( "SELECT c FROM Customer c", Customer.class );

        return query.setFirstResult(( pageNumber - 1 ) * MAX_RESULTS )
                .setMaxResults(MAX_RESULTS)
                .getResultList();
    }
    public long getPagesCount() {
        Query queryTotal = entityManager.createQuery( "SELECT COUNT(c.id) FROM Customer c" );
        long countResult = (long) queryTotal.getSingleResult();

        long finalPage = countResult % MAX_RESULTS > 0 ? 1 : 0 ;

        return (countResult / MAX_RESULTS) + finalPage;
    }

}
