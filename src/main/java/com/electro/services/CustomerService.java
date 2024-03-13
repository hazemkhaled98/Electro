package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.repositries.CustomerRepositry;
import com.electro.presentation.dto.LoginDTO;
import com.electro.presentation.dto.SignUpDTO;
import com.electro.presentation.dto.UpdateProfileDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class CustomerService {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private CustomerService(){
    }

    public static Optional<Customer> login(LoginDTO loginDTO){
        return Database.doInTransaction(em -> {
            try{
                CustomerRepositry customerRepositry = new CustomerRepositry(em);
                Optional<Customer> customer = customerRepositry.getCustomerByEmail(loginDTO.getEmail());
                if(customer.isPresent()){
                    if(passwordEncoder.matches(loginDTO.getPassword(), customer.get().getPassword())){
                        return customer;
                    }
                }
                return Optional.empty();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return Optional.empty();
            }
        });

    }

    public static Optional<Customer> signup(SignUpDTO signUpDTO){
        return Database.doInTransaction(em -> {
            try {
                CustomerRepositry customerRepositry = new CustomerRepositry(em);
                Customer newCustomer = new Customer();
                newCustomer.setCustomerName(signUpDTO.getName());
                newCustomer.setEmail(signUpDTO.getEmail().toLowerCase());
                newCustomer.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
                newCustomer.setJob(signUpDTO.getJob());
                newCustomer.setCountry(signUpDTO.getCountry());
                newCustomer.setCity(signUpDTO.getCity());
                newCustomer.setStreetNo(signUpDTO.getStreetNo());
                newCustomer.setStreetName(signUpDTO.getStreetName());
                newCustomer.setBirthday(signUpDTO.getBirthdate());
                customerRepositry.create(newCustomer);
                return Optional.of(newCustomer);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return Optional.empty();
            }
        });
    }
    public static Optional<Customer> updateProfile(UpdateProfileDTO updateProfileDTO,Customer sessionCustomer){
        return Database.doInTransaction(em -> {
                try{
                    CustomerRepositry customerRepositry = new CustomerRepositry(em);
                    mapUpdateProfileDTOToCustomer(updateProfileDTO,sessionCustomer);
                    return Optional.of(customerRepositry.update(sessionCustomer));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return Optional.empty();
                }
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
