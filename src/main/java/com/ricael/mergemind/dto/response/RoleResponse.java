package com.ricael.mergemind.dto.response;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.enums.Stacks;

import java.util.List;

public record RoleResponse(
        Long id,
        String name,
        String description,
        String projectName,
        List<Stacks> stacks) {
}
