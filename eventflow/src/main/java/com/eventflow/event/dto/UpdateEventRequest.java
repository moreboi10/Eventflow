package com.eventflow.event.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record UpdateEventRequest(
        @NotBlank
        @Size(max = 200)
        String title,

        String description,

        @Size(max = 100)
        String category,

        @Size(max = 255)
        String location,

        @NotNull
        @Future
        LocalDateTime startTime,

        @NotNull
        @Future
        LocalDateTime endTime,

        @NotNull
        @Min(0)
        Integer volunteerCapacity,

        @NotNull
        @Future
        LocalDateTime applicationDeadline
) {
}
