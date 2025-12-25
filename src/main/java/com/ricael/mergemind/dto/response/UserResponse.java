package com.ricael.mergemind.dto.response;

public record UserResponse(Long id,
                           String name,
                           String email,
                           String bio,
                           String github_url,
                           String photo_url) {
}

