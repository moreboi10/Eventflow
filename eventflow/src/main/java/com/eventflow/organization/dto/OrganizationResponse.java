package com.eventflow.organization.dto;

import lombok.Builder;

@Builder
public record OrganizationResponse(
        Long id,
        String name,
        String description,
        Long ownerId
) {
}
