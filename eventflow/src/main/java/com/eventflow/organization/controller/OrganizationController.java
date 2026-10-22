
package com.eventflow.organization.controller;

import com.eventflow.organization.dto.CreateOrganizationRequest;
import com.eventflow.organization.dto.OrganizationResponse;
import com.eventflow.organization.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrganizationResponse> getOrganization(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                organizationService.getOrganizationById(id)
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ORGANIZER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> createOrganization(
            @Valid @RequestBody CreateOrganizationRequest request) {

        OrganizationResponse organization =
                organizationService.createOrganization(request);

        Map<String, Object> response = Map.of(
                "id", organization.id(),
                "name", organization.name(),
                "description", organization.description() == null
                        ? "" : organization.description()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
