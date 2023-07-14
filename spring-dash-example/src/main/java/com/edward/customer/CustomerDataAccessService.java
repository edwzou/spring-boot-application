package com.edward.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataAccessService implements CustomerDAO {
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
}
