package com.skills.request;

import jakarta.validation.constraints.NotBlank;

public record JoinGroupRequest(
        @NotBlank
        String groupCode
) {
}
