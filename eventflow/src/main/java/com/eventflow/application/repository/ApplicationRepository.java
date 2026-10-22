
package com.eventflow.application.repository;

import com.eventflow.application.entity.Application;
import com.eventflow.application.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    boolean existsByEventIdAndVolunteerId(Long eventId, Long volunteerId);

    List<Application> findByVolunteerId(Long volunteerId);

    List<Application> findByEventId(Long eventId);

    long countByEventIdAndStatus(
            Long eventId,
            ApplicationStatus status
    );
}
