package com.ricael.mergemind.dto.response;

import com.ricael.mergemind.domain.enums.Status;

public record ProjectResponse(String title,
                             String description,
                             String banner_url,
                             Status status,
                             UserResponse user) {
}
