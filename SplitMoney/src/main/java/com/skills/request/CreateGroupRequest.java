package com.skills.request;

import jakarta.validation.constraints.NotBlank;

public record CreateGroupRequest(
        @NotBlank
        String name,
        String description,
        @NotBlank
        String creatorId
) {
}
