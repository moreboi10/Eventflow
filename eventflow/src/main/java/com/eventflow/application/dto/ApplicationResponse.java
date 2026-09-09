package com.eventflow.application.dto;

import com.eventflow.application.entity.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationResponse(

        Long id,
        Long eventId,
        Long volunteerId,
        ApplicationStatus status,
        String motivation,
        LocalDateTime appliedAt,
        LocalDateTime updatedAt
) {
}
