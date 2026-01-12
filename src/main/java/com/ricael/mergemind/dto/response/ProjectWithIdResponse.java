package com.ricael.mergemind.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricael.mergemind.domain.enums.Status;

public record ProjectWithIdResponse(Long id,
                                    String title,
                                    String description,
                                    @JsonProperty("banner_url") String banner_url,
                                    Status status,
                                    UserResponse user) {
}
