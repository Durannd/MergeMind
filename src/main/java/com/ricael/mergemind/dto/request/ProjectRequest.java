package com.ricael.mergemind.dto.request;

import com.ricael.mergemind.domain.enums.Status;

public record ProjectRequest(String title,
                             String description,
                             String banner_url,
                             Status status,
                             UserRequest user) {
}
