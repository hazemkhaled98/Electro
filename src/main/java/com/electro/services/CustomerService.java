package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.repositries.CustomerRepositry;
import com.electro.presentation.dto.LoginDTO;

import java.util.Optional;

public class CustomerService {

    private CustomerService(){
    }

    public static Optional<Customer> login(LoginDTO loginDTO){
        return Database.doInTransaction(em -> {
            CustomerRepositry customerRepositry = new CustomerRepositry(em);
            Optional<Customer> customer = customerRepositry.getCustomerByEmail(loginDTO.getEmail());
            if(customer.isPresent()){
                if(customer.get().getPassword().equals(loginDTO.getPassword())){
                    return customer;
                }
            }
            return Optional.empty();
        });

    }
}
