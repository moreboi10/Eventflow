package com.eventflow.application.controller;

import com.eventflow.application.entity.Application;
import com.eventflow.application.service.ApplicationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/{id}")
public Application getApplication(@PathVariable Long id) {
    return applicationService.getApplicationById(id);
}

@GetMapping
public List<Application> getAllApplications() {
    return applicationService.getAllApplications();
}

}