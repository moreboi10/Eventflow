package com.eventflow.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventflow.application.entity.Application;

public interface  ApplicationRepository extends JpaRepository<Application,Long>{
    
}
