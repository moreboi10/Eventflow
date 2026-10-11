package com.eventflow.organization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateOrganizationRequest(
        @NotBlank
        @Size(max = 150)
        String name,

        String description

) {
}
