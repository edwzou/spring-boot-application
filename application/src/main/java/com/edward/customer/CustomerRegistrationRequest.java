package com.edward.customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {
}
