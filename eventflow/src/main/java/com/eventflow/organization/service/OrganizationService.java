package com.eventflow.organization.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eventflow.organization.entity.Organization;
import com.eventflow.organization.repository.OrganizationRepository;

@Service 
public class OrganizationService {

    private OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    public Organization saveOrganization(Organization organization){
         return organizationRepository.save(organization);
    }

    public Organization getOrganizationById(Long id) {
    return organizationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    public List<Organization> getAllOrganizations() {
    return organizationRepository.findAll();
    }

    public Organization updateOrganization(Long id , Organization organization){
        Organization existingOrganization  = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found"));
        existingOrganization.setName(organization.getName());
        existingOrganization.setDescription(organization.getDescription());
        return organizationRepository.save(existingOrganization);
    }

    public void deleteOrganization(Long id){
       Organization existingOrganization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found"));
       organizationRepository.delete(existingOrganization);
    }
    
}
