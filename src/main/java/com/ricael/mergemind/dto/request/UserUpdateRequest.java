package com.ricael.mergemind.dto.request;

public record UserUpdateRequest (
    String name,
    String email,
    String bio,
    String photo_url,
    String github_url){
}
