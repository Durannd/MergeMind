package com.ricael.mergemind.dto.mapper;

import com.ricael.mergemind.domain.Role;
import com.ricael.mergemind.dto.request.RoleRequest;
import com.ricael.mergemind.dto.response.RoleResponse;

public final class RoleMapper {
    private RoleMapper() {
    }

    public static Role toEntity(RoleRequest request) {
        if (request == null) {
            return null;
        }
        Role role = new Role();
        role.setName(request.name());
        role.setDescription(request.description());
        role.setProject(request.project());
        role.setStacks(request.stacks());
        return role;
    }

    public static RoleResponse toResponse(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getProject(),
                role.getStacks()
        );
    }
}

