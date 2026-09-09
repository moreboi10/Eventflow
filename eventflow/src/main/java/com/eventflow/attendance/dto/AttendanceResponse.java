package com.eventflow.attendance.dto;

import com.eventflow.attendance.entity.AttendanceStatus;

import java.time.LocalDateTime;

public record AttendanceResponse(

        Long id,
        Long applicationId,
        LocalDateTime checkInTime,
        LocalDateTime checkOutTime,
        AttendanceStatus status

) {
}
