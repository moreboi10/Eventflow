package com.eventflow.application.service;

import com.eventflow.application.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import com.eventflow.application.entity.Application;
import com.eventflow.application.entity.ApplicationStatus;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application saveApplication(Application application) {
    return applicationRepository.save(application);
}

public Application getApplicationById(Long id) {
    return applicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));
}

public List<Application> getAllApplications() {
    return applicationRepository.findAll();
}

public Application updateApplicationStatus(Long id, ApplicationStatus status) {

    Application application = applicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));

    application.setStatus(status);

    return applicationRepository.save(application);
}

public void deleteApplication(Long id) {

    Application application = applicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));

    applicationRepository.delete(application);
}

}