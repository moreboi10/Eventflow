package com.eventflow.event.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateEventRequest(

        @NotNull
        @Size( max = 100)
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
        LocalDateTime applicationDeadline,

        @NotNull
        Long organizationId

) {
}
