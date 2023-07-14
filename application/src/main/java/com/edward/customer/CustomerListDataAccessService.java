package com.edward.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO {
    // database
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer Edward = new Customer(
                1,
                "Edward",
                "edward@gmail.com",
                22
        );
        customers.add(Edward);
        Customer Bea = new Customer(
                2,
                "Bea",
                "bea@gmail.com",
                23
        );
        customers.add(Bea);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return customers.stream()
                .anyMatch(customer -> customer.getId().equals(id));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .ifPresent(customer -> customers.remove(customer));
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }
}
