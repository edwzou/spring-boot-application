package com.edward;

import com.edward.customer.Customer;
import com.edward.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer Edward = new Customer(
                    "Edward",
                    "edward@gmail.com",
                    22
            );
            Customer Bea = new Customer(
                    "Bea",
                    "bea@gmail.com",
                    23
            );

            List<Customer> customers = List.of(Edward, Bea);
            //customerRepository.saveAll(customers);
        };
    }
}
