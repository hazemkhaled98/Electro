package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.repositries.CustomerRepositry;
import com.electro.presentation.dto.LoginDTO;
import com.electro.presentation.dto.SignUpDTO;
import com.electro.presentation.dto.UpdateProfileDTO;

import java.math.BigDecimal;
import java.util.Objects;
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

    public  static boolean signup(SignUpDTO signUpDTO){
        return Database.doInTransaction(em -> {
            CustomerRepositry customerRepositry = new CustomerRepositry(em);
            Optional<Customer> customer = customerRepositry.getCustomerByEmail(signUpDTO.getEmail());
            if (customer.isPresent()) {
                return false;
            } else {
                Customer customerEntity = new Customer();
                customerEntity.setCustomerName(signUpDTO.getName());
                customerEntity.setEmail(signUpDTO.getEmail());
                customerEntity.setPassword(signUpDTO.getPassword());
                customerEntity.setJob(signUpDTO.getJob());
                customerEntity.setCountry(signUpDTO.getCountry());
                customerEntity.setCity(signUpDTO.getCity());
                customerEntity.setStreetNo(signUpDTO.getStreetNo());
                customerEntity.setStreetName(signUpDTO.getStreetName());
                customerEntity.setBirthday(signUpDTO.getBirthdate());
                customerRepositry.create(customerEntity);

                return true;
            }
        });
    }
    public static Customer updateProfile(UpdateProfileDTO updateProfileDTO,Customer sessionCustomer){
        return Database.doInTransaction(em -> {
            CustomerRepositry customerRepositry = new CustomerRepositry(em);
            mapUpdateProfileDTOToCustomer(updateProfileDTO,sessionCustomer);
            customerRepositry.update(sessionCustomer);
            return sessionCustomer;
        });
    }
    public static void mapUpdateProfileDTOToCustomer(UpdateProfileDTO updateProfileDTO,Customer customer) {

        if (updateProfileDTO.getName() != null && !updateProfileDTO.getName().isEmpty()) {
            customer.setCustomerName(updateProfileDTO.getName());
        }
        if (updateProfileDTO.getJob() != null && !updateProfileDTO.getJob().isEmpty()) {
            customer.setJob(updateProfileDTO.getJob());
        }
        if (updateProfileDTO.getCountry() != null && !updateProfileDTO.getCountry().isEmpty()) {
            customer.setCountry(updateProfileDTO.getCountry());
        }
        if (updateProfileDTO.getCity() != null && !updateProfileDTO.getCity().isEmpty()) {
            customer.setCity(updateProfileDTO.getCity());
        }
        if (updateProfileDTO.getStreetNo() != null && !updateProfileDTO.getStreetNo().isEmpty()) {
            customer.setStreetNo(updateProfileDTO.getStreetNo());
        }
        if (updateProfileDTO.getStreetName() != null && !updateProfileDTO.getStreetName().isEmpty()) {
            customer.setStreetName(updateProfileDTO.getStreetName());
        }
        if (!Objects.equals(updateProfileDTO.getCreditLimit(), BigDecimal.valueOf(-1))) {
            customer.setCreditLimit(updateProfileDTO.getCreditLimit());
        }
    }
}
