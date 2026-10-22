
package com.eventflow.event.dto;

import com.eventflow.event.entity.EventStatus;

import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        String title,
        String description,
        String category,
        String location,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer volunteerCapacity,
        LocalDateTime applicationDeadline,
        EventStatus status,
        Long organizationId,
        String organizationName
) {}
