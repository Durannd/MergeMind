package com.ricael.mergemind.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricael.mergemind.domain.enums.Stacks;
import com.ricael.mergemind.domain.enums.Status;

import java.util.List;

public record ProjectResponse(String title,
                             String description,
                              @JsonProperty("banner_url") String banner_url,
                             Status status,
                                @JsonProperty("short_description")
                             String short_description,
                             @JsonProperty("repository_url") String repository_url,
                             List<Stacks> stacks,
                             UserResponse user) {
}
