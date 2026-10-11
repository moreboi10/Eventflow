package com.eventflow.event.repository;

import com.eventflow.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends  JpaRepository<Event,Long>{
    
}
