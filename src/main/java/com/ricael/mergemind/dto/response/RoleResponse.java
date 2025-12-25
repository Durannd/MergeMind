package com.ricael.mergemind.dto.response;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.enums.Stacks;

import java.util.List;

public record RoleResponse(
        Long id,
        String name,
        String description,
        Project project,
        List<Stacks> stacks) {
}
