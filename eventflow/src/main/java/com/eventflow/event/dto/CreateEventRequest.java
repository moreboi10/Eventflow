
package com.eventflow.event.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateEventRequest(

        @NotBlank
        @Size(max = 200)
        String title,

        String description,

        @NotBlank
        @Size(max = 100)
        String category,

        @NotBlank
        @Size(max = 200)
        String location,

        @NotNull
        LocalDateTime startTime,

        @NotNull
        LocalDateTime endTime,

        @NotNull
        @Min(1)
        Integer volunteerCapacity,

        @NotNull
        LocalDateTime applicationDeadline,

        @NotNull
        Long organizationId
) {}
