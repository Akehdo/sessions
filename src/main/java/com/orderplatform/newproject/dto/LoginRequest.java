package com.orderplatform.newproject.dto;

public record LoginRequest(
        String email,
        String password
) {
}
