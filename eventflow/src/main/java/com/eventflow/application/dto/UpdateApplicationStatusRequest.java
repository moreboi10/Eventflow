package com.eventflow.application.dto;

import com.eventflow.application.entity.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateApplicationStatusRequest(

        @NotNull
        ApplicationStatus status
) {
}
