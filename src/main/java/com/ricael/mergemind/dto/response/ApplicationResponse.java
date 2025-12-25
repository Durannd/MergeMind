package com.ricael.mergemind.dto.response;

import com.ricael.mergemind.domain.enums.Status;
import com.ricael.mergemind.dto.request.UserIdRequest;

public record ApplicationResponse(Long id,
                                  Status status,
                                  UserResponse user,
                                  RoleResponse role
                                 ) {
}
