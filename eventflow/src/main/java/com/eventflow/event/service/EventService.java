
package com.eventflow.event.service;

import com.eventflow.event.dto.CreateEventRequest;
import com.eventflow.event.dto.EventResponse;
import com.eventflow.event.entity.Event;
import com.eventflow.event.entity.EventStatus;
import com.eventflow.event.repository.EventRepository;
import com.eventflow.exception.ResourceNotFoundException;
import com.eventflow.organization.entity.Organization;
import com.eventflow.organization.repository.OrganizationRepository;
import com.eventflow.user.entity.User;
import com.eventflow.user.repository.UserRepository;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public EventService(
            EventRepository eventRepository,
            OrganizationRepository organizationRepository,
            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public EventResponse createEvent(CreateEventRequest request) {
        User currentUser = getCurrentUser();

        Organization organization = organizationRepository
                .findById(request.organizationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Organization not found"));

        boolean isAdmin = currentUser.getRole().name().equals("ADMIN");
        boolean isOwner = organization.getOwner().getId()
                .equals(currentUser.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException(
                    "You cannot create events for this organization");
        }

        if (request.endTime().isBefore(request.startTime())
                || request.endTime().isEqual(request.startTime())) {
            throw new IllegalArgumentException(
                    "Event end time must be after start time");
        }

        if (request.applicationDeadline().isAfter(request.startTime())) {
            throw new IllegalArgumentException(
                    "Application deadline cannot be after event start time");
        }

        Event event = new Event();
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setCategory(request.category());
        event.setLocation(request.location());
        event.setStartTime(request.startTime());
        event.setEndTime(request.endTime());
        event.setVolunteerCapacity(request.volunteerCapacity());
        event.setApplicationDeadline(request.applicationDeadline());
        event.setOrganization(organization);
        event.setStatus(EventStatus.PUBLISHED);

        return toResponse(eventRepository.save(event));
    }

    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        return toResponse(event);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("Authentication required");
        }

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    private EventResponse toResponse(Event event) {
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory(),
                event.getLocation(),
                event.getStartTime(),
                event.getEndTime(),
                event.getVolunteerCapacity(),
                event.getApplicationDeadline(),
                event.getStatus(),
                event.getOrganization().getId(),
                event.getOrganization().getName()
        );
    }
}
