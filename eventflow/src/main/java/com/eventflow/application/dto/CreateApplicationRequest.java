package com.eventflow.application.dto;

import jakarta.validation.constraints.Size;

public record CreateApplicationRequest(
        @Size(max = 2000)
        String motivation
) {
}
