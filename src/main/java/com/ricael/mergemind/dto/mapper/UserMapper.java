package com.ricael.mergemind.dto.mapper;

import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.dto.response.UserResponse;

public final class UserMapper {
    private UserMapper() {
    }

    public static User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setBio(request.bio());
        user.setGithub_url(request.github_url());
        user.setPhoto_url(request.photo_url());
        return user;
    }

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBio(),
                user.getGithub_url(),
                user.getPhoto_url()
        );
    }
}

