package com.ricael.mergemind.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricael.mergemind.domain.enums.Status;

public record ProjectRequest(String title,
                             String description,
                             @JsonProperty("banner_url") String banner_url,
                             Status status,
                             UserRequest user) {
}
