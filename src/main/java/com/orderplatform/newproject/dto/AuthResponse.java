package com.orderplatform.newproject.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
