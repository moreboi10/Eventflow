package com.eventflow.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventflow.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
    
}
