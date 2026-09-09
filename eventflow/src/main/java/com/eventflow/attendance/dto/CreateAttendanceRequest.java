package com.eventflow.attendance.dto;

import com.eventflow.attendance.entity.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAttendanceRequest(
        @NotNull
        AttendanceStatus status,

        LocalDateTime checkInTime,

        LocalDateTime checkOutTime
) {
}
