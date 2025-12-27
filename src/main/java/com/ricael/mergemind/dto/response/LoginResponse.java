package com.ricael.mergemind.dto.response;

public record LoginResponse(String token, UserResponse user) {
}