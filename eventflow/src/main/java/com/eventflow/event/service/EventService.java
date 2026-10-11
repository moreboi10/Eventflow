package com.eventflow.event.service;

import org.springframework.stereotype.Service;
import com.eventflow.event.entity.Event;
import com.eventflow.event.repository.EventRepository;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event updateEvent(Long id , Event event){
        Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("No event found"));

        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setCategory(event.getCategory());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setStartTime(event.getStartTime());
        existingEvent.setEndTime(event.getEndTime());
        existingEvent.setVolunteerCapacity(event.getVolunteerCapacity());
        existingEvent.setApplicationDeadline(event.getApplicationDeadline());

     return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id) {

    Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));

    eventRepository.delete(event);
}

}
