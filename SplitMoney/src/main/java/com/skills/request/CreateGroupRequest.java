package com.skills.request;

import jakarta.validation.constraints.NotBlank;

public record CreateGroupRequest(
        @NotBlank
        String name,
        String description,
        String creatorId // Removed @NotBlank since it is set on the server side
) {
}
