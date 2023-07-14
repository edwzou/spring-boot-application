package com.edward.customer;

import com.edward.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFound("customer with id %s not found".formatted(id)));
    }
}
