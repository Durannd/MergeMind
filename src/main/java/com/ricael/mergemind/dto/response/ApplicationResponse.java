package com.ricael.mergemind.dto.response;

import com.ricael.mergemind.domain.enums.Status;

public record ApplicationResponse(Long id,
                                  Status status,
                                  UserResponse user,
                                  RoleResponse role
                                 ) {
}
