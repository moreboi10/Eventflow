package com.eventflow.organization.dto;

public record OrganizationResponse(
        Long id,
        String name,
        String description,
        Long ownerId
) {
}
