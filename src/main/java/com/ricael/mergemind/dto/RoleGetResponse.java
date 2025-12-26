package com.ricael.mergemind.dto;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.enums.Stacks;

import java.util.List;

public record RoleGetResponse(Long id,
                              String name,
                              String description,
                              List<Stacks> stacks) {
}
