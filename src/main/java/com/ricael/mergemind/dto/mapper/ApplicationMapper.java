package com.ricael.mergemind.dto.mapper;

import com.ricael.mergemind.domain.Application;
import com.ricael.mergemind.domain.Role;
import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.dto.request.ApplicationRequest;
import com.ricael.mergemind.dto.response.ApplicationResponse;

public final class ApplicationMapper {
    private ApplicationMapper() {
    }

    public static Application toEntity(ApplicationRequest request) {
        if (request == null) {
            return null;
        }
        Application application = new Application();
        application.setStatus(request.status());
        User user = new User();
        user.setId(request.user().id());
        application.setUser(user);
        Role role = new Role();
        role.setId(request.role().id());
        application.setRole(role);
        return application;
    }

    public static ApplicationResponse toResponse(Application application) {
        if (application == null) {
            return null;
        }
        return new ApplicationResponse(
                application.getId(),
                application.getStatus(),
                UserMapper.toResponse(application.getUser()),
                RoleMapper.toResponse(application.getRole())
        );
    }
}

