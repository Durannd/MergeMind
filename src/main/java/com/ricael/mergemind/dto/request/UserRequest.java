package com.ricael.mergemind.dto.request;

public record UserRequest(String name,
                          String email,
                          String bio,
                          String github_url,
                          String photo_url) {
}
