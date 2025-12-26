package com.ricael.mergemind.dto.request;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.enums.Stacks;
import jakarta.persistence.*;

import java.util.List;

public record RoleRequest(
        String name,
        String description,
        List<Stacks> stacks) {
}
