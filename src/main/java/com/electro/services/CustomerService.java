package com.electro.services;

import com.electro.persistence.Database;
import com.electro.persistence.entities.Customer;
import com.electro.persistence.entities.Order;
import com.electro.persistence.entities.OrderItem;
import com.electro.persistence.entities.Product;
import com.electro.persistence.repositries.OrderItemRepository;
import com.electro.persistence.repositries.OrderRepository;
import com.electro.persistence.repositries.ProductRepository;
import com.electro.presentation.dto.*;
import com.electro.persistence.repositries.CustomerRepository;
import com.electro.presentation.dto.LoginDTO;
import com.electro.presentation.dto.SignUpDTO;
import com.electro.presentation.dto.UpdateProfileDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerService {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private CustomerService(){
    }

    public static Optional<Customer> login(LoginDTO loginDTO){
        return Database.doInTransaction(em -> {
            try{
                CustomerRepository customerRepositry = new CustomerRepository(em);
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
                CustomerRepository customerRepositry = new CustomerRepository(em);
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
                    CustomerRepository customerRepositry = new CustomerRepository(em);
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
    public static List<CustomerDTO> getAllCustomers(){
        return Database.doInTransaction(em -> {
            List<CustomerDTO> customerDTOS = new ArrayList<>();
            CustomerRepository customerRepositry = new CustomerRepository(em);
            List<Customer> customers = customerRepositry.getAll();
            for (Customer customer : customers) {
                CustomerDTO customerDTO = new CustomerDTO();
                mapCustomerToCustomerDTO(customerDTO ,  customer);
                customerDTOS.add(customerDTO);
            }
            return customerDTOS;
        });
    }
    public static void mapCustomerToCustomerDTO(CustomerDTO customerDTO , Customer customer) {
        customerDTO.setId(customer.getId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setJob(customer.getJob());
        customerDTO.setCountry(customer.getCountry());
        customerDTO.setCity(customer.getCity());
        customerDTO.setStreetNo(customer.getStreetNo());
        customerDTO.setStreetName(customer.getStreetName());
        customerDTO.setCreditLimit(customer.getCreditLimit());
        customerDTO.setBirthday(customer.getBirthday());
    }
    public static CustomerDTO getCustomerById(Integer customerID){
        return Database.doInTransaction(em -> {
           CustomerRepository customerRepositry = new CustomerRepository(em);
           Customer customer = customerRepositry.get(customerID).get();
              CustomerDTO customerDTO = new CustomerDTO();
                mapCustomerToCustomerDTO(customerDTO ,  customer);
                return customerDTO;
        });
    }
    public static List<OrderDTO> getCustomerOrders(Integer customerID){
        return Database.doInTransaction(em -> {
            List<OrderDTO> orderDTOS = new ArrayList<>();
            CustomerRepository customerRepositry = new CustomerRepository(em);
            Customer customer = customerRepositry.get(customerID).get();
            for (Order order : customer.getOrders()) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId(order.getId());
                orderDTO.setCustomer(order.getCustomer());
                orderDTO.setOrderedAt(order.getOrderedAt());
                orderDTO.setOrderItems(order.getOrderItems());
                orderDTOS.add(orderDTO);
            }
            return orderDTOS;
        });
    }
    public static List<OrderItemDTO> getOrderItemsForOrder(Integer orderID){
        return Database.doInTransaction(em -> {
            List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
            OrderRepository orderRepository = new OrderRepository(em);
            Order order = orderRepository.get(orderID).get();
            for (OrderItem orderItem : order.getOrderItems()) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setId(orderItem.getId());
                orderItemDTO.setOrder(orderItem.getOrder());
                orderItemDTO.setProduct(orderItem.getProduct());
                orderItemDTO.setQuantity(orderItem.getQuantity());
                orderItemDTO.setAmount(orderItem.getAmount());
                orderItemDTOS.add(orderItemDTO);
            }
            return orderItemDTOS;
        });
    }
    public static List<Customer> getPageOfCustomers(String page) {
        try {
            int pageNumber = Integer.parseInt(page);
            return Database.doInTransaction(em -> {
                CustomerRepository customerRepository = new CustomerRepository(em);
                return customerRepository.getPageOfCustomer(pageNumber);
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    public static long getPagesCount() {
        return Database.doInTransaction(em -> {
            CustomerRepository customerRepository = new CustomerRepository(em);
            return customerRepository.getPagesCount();
        });
    }

}
