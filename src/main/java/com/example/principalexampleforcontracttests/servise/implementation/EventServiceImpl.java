package com.example.principalexampleforcontracttests.servise.implementation;

import com.example.principalexampleforcontracttests.entity.Event;
import com.example.principalexampleforcontracttests.enums.EventType;
import com.example.principalexampleforcontracttests.exceptions.EventNotFoundException;
import com.example.principalexampleforcontracttests.repository.ExampleEventRepository;
import com.example.principalexampleforcontracttests.servise.EventService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private ExampleEventRepository eventRepository;

    @Autowired
    public EventServiceImpl(ExampleEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        log.debug("Creating event: {}", event);
        Event searchingEvent;
        try {
            searchingEvent = fetchEventById(event.getId());
        }
        catch (EventNotFoundException exception) {
            event.setId(ObjectId.get());
            return eventRepository.save(event);
        }
        return searchingEvent;
    }

    @Override
    public Event fetchEventById(ObjectId id) throws EventNotFoundException {
        log.debug("Fetching event by id: {}", id);
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", id.toString()));
        }
    }

    @Override
    public Event fetchEventByEventType(EventType eventType) throws EventNotFoundException {
        log.debug("Fetching event by type: {}", eventType);
        Optional<Event> eventOptional = eventRepository.findByEventType(eventType);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with event type: %s does not exists.", eventType));
        }
    }

    @Override
    public Event fetchEventBySummary(String summary) throws EventNotFoundException {
        log.debug("Fetching event by summary: {}", summary);
        Optional<Event> eventOptional = eventRepository.findBySummary(summary);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with summary: %s does not exists.", summary));
        }
    }

    @Override
    public Event fetchEventByDate(Date date) throws EventNotFoundException {
        log.debug("Fetching event by date: {}", date);
        Optional<Event> eventOptional = eventRepository.findByDate(date);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with date: %s does not exists.", date.toString()));
        }
    }

    @Override
    public Event updateEvent(ObjectId id, Event event) throws EventNotFoundException {
        log.debug("Updating event with id: {} on event: {}", id, event);
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent()) {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", id.toString()));
        }
        return eventRepository.save(event);
    }

    @Override
    public Event addParticipants(ObjectId eventId, Set<ObjectId> participantsId) {
        log.debug("Adding to event: {} participants : {}", eventId, participantsId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", eventId.toString()));
        }
        eventOptional.get().getParticipants().addAll(participantsId);
        return eventRepository.save(eventOptional.get());
    }

    @Override
    public void deleteEventById(ObjectId id) throws EventNotFoundException {
        log.debug("Deleting event with id: {} ", id);
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent()) {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists", id.toString()));
        }
    }

}
