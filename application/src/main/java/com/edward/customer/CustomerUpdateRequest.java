package com.edward.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
