package com.eventflow.organisation.dto;

public record OrganizationResponse(
        Long id,
        String name,
        String description,
        Long ownerId
) {
}
