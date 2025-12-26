package com.ricael.mergemind.dto.request;

public record UserRequest(String name,
                          String email,
                          String password,
                          String bio,
                          String github_url,
                          String photo_url) {
}
