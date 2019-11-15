package com.example.principalexampleforcontracttests.servise.implementation;

import com.example.principalexampleforcontracttests.entity.Event;
import com.example.principalexampleforcontracttests.enums.EventType;
import com.example.principalexampleforcontracttests.exceptions.EventNotFoundException;
import com.example.principalexampleforcontracttests.repository.ExampleEventRepository;
import com.example.principalexampleforcontracttests.servise.EventService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    private ExampleEventRepository eventRepository;

    @Autowired
    public EventServiceImpl(ExampleEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event fetchEventById(ObjectId id) throws EventNotFoundException {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", id.toString()));
        }
    }

    @Override
    public Event fetchEventByEventType(EventType eventType) throws EventNotFoundException {
        Optional<Event> eventOptional = eventRepository.findByEventType(eventType);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with event type: %s does not exists.", eventType));
        }
    }

    @Override
    public Event fetchEventBySummary(String summary) throws EventNotFoundException {
        Optional<Event> eventOptional = eventRepository.findBySummary(summary);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with summary: %s does not exists.", summary));
        }
    }

    @Override
    public Event fetchEventByDate(Date date) throws EventNotFoundException {
        Optional<Event> eventOptional = eventRepository.findByDate(date);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with date: %s does not exists.", date.toString()));
        }
    }

    @Override
    public Event updateEvent(ObjectId id, Event event) throws EventNotFoundException {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent()) {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", id.toString()));
        }
        event.setId(id);
        return eventRepository.save(event);
    }

    @Override
    public Event addParticipants(ObjectId eventId, Set<ObjectId> participantsId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!eventOptional.isPresent()) {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", eventId.toString()));
        }
        eventOptional.get().getParticipants().addAll(participantsId);
        return eventRepository.save(eventOptional.get());
    }

    @Override
    public void deleteEventById(ObjectId id) throws EventNotFoundException {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent()) {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists", id.toString()));
        }
    }

}
