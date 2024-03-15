package com.electro.persistence.repositries;

import com.electro.persistence.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductRepository extends Repository<Product> {

    private final static int MAX_RESULTS = 5;
    public ProductRepository(EntityManager entityManager) {
        super(entityManager);
        super.setType(Product.class);
    }


    public long getPagesCount() {
        Query queryTotal = entityManager.createQuery( "SELECT COUNT(p.id) FROM Product p" );
        long countResult = (long) queryTotal.getSingleResult();

        long finalPage = countResult % MAX_RESULTS > 0 ? 1 : 0 ;

        return (countResult / MAX_RESULTS) + finalPage;
    }

    public List<Product> getPageOfProduct(int pageNumber) {
        TypedQuery<Product> query = entityManager.createQuery( "SELECT p FROM Product p", Product.class );

        return query.setFirstResult(( pageNumber - 1 ) * MAX_RESULTS )
                .setMaxResults(MAX_RESULTS)
                .getResultList();
    }


}
