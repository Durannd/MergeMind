package com.ricael.mergemind.dto.request;

import com.ricael.mergemind.domain.enums.Status;

public record ApplicationRequest(Long id,
                                 Status status,
                                 UserRefRequest user,
                                 RoleRefRequest role
                                 ) {
}
