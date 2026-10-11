package com.eventflow.attendance.service;

import com.eventflow.attendance.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import com.eventflow.attendance.entity.Attendance;
import com.eventflow.attendance.entity.AttendanceStatus;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }

    public Attendance updateAttendance(Long id, AttendanceStatus status) {

    Attendance attendance = attendanceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Attendance not found"));

    attendance.setStatus(status);

    return attendanceRepository.save(attendance);
}
}