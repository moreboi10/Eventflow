
package com.eventflow.organization.service;

import java.time.LocalDateTime;
import java.util.List;

import com.eventflow.exception.ResourceNotFoundException;
import com.eventflow.organization.dto.CreateOrganizationRequest;
import com.eventflow.organization.dto.OrganizationResponse;
import com.eventflow.organization.entity.Organization;
import com.eventflow.organization.repository.OrganizationRepository;
import com.eventflow.user.entity.User;
import com.eventflow.user.repository.UserRepository;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private static final String RESOURCE_ERROR_MESSAGE =
            "Organization resource not found";

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public OrganizationService(
            OrganizationRepository organizationRepository,
            UserRepository userRepository) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public OrganizationResponse getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(RESOURCE_ERROR_MESSAGE));

        return convertToResponse(organization);
    }

    public List<OrganizationResponse> getAllOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public OrganizationResponse updateOrganization(
            Long id, Organization organization) {

        Organization existingOrganization =
                organizationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        RESOURCE_ERROR_MESSAGE));

        existingOrganization.setName(organization.getName());
        existingOrganization.setDescription(organization.getDescription());
        existingOrganization.setUpdatedAt(LocalDateTime.now());

        Organization updatedOrganization =
                organizationRepository.save(existingOrganization);

        return convertToResponse(updatedOrganization);
    }

    public void deleteOrganization(Long id) {
        Organization existingOrganization =
                organizationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        RESOURCE_ERROR_MESSAGE));

        organizationRepository.delete(existingOrganization);
    }

    public OrganizationResponse createOrganization(
            CreateOrganizationRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new AuthenticationCredentialsNotFoundException(
                    "Authentication required");
        }

        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Authenticated user not found"));

        LocalDateTime now = LocalDateTime.now();

        Organization organization = new Organization();
        organization.setName(request.name());
        organization.setDescription(request.description());
        organization.setOwner(currentUser);
        organization.setCreatedAt(now);
        organization.setUpdatedAt(now);

        Organization savedOrganization =
                organizationRepository.save(organization);

        return convertToResponse(savedOrganization);
    }

    private OrganizationResponse convertToResponse(
            Organization organization) {

        return OrganizationResponse.builder()
                .id(organization.getId())
                .name(organization.getName())
                .description(organization.getDescription())
                .ownerId(organization.getOwner().getId())
                .build();
    }
}
