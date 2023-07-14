package com.edward.customer;

import com.edward.exception.DuplicateResourceException;
import com.edward.exception.RequestValidationException;
import com.edward.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer id) {
        return customerDAO.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id %s not found".formatted(id)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        String email = customerRegistrationRequest.email();
        if (customerDAO.existsPersonWithEmail(email)) {
            throw new DuplicateResourceException("Email already taken");
        }
        String name = customerRegistrationRequest.name();
        Integer age = customerRegistrationRequest.age();
        customerDAO.insertCustomer(
                new Customer(
                        name,
                        email,
                        age
                )
        );
    }

    public void deleteCustomerById(Integer id) {
        if (!customerDAO.existsPersonWithId(id)) {
            throw new ResourceNotFoundException(
                    "Customer with id %s is not found".formatted(id)
            );
        }
        customerDAO.deleteCustomerById(id);
    }


    public void updateCustomer(Integer id, CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomer(id);
        boolean changes = false;
        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }
        if (updateRequest.email() != null & !updateRequest.email().equals(customer.getEmail())) {
            if (customerDAO.existsPersonWithEmail(updateRequest.email())) {
                throw new DuplicateResourceException(
                        "Email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }
        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("No data changes found");
        }
        customerDAO.updateCustomer(customer);
    }
}
