package com.flamelab.eventnotification.servise.implementation;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.enums.EventType;
import com.flamelab.eventnotification.repository.EventRepository;
import com.flamelab.eventnotification.servise.EventService;
import com.flamelab.eventnotification.exceptions.EventNotFoundException;
import com.flamelab.eventnotification.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private UserService userService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    @Override
    public Event createEvent(EventType eventType, String summary, Set<ObjectId> participants, Date date) {
        log.debug("Creating event with event type {}, summary {}, participants {}, at date {}",
                eventType, summary, participants, date);
        Set<User> participantsFromStorage = userService.fetchUsersByIds(participants);
        Set<ObjectId> resultParticipants = new HashSet<>();
        for (User user : participantsFromStorage) {
            resultParticipants.add(user.getId());
        }

        Event event;
        try {
            event = fetchEventBySummary(summary);
        } catch (EventNotFoundException exception) {
            event = Event.builder()
                    .id(ObjectId.get())
                    .eventType(eventType)
                    .summary(summary)
                    .participants(resultParticipants)
                    .date(date)
                    .build();
            return eventRepository.save(event);
        }
        log.debug("Create result event with event id {}, event type {}, summary {}, participants {}, at date {}",
                event.getId(), event.getEventType(), event.getSummary(), event.getParticipants(), event.getDate());
        return event;
    }

    @Override
    public Event addParticipants(ObjectId eventId, Set<ObjectId> participantsIds) throws EventNotFoundException {
        log.debug("Adding to event: {} participants : {}", eventId, participantsIds);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.getParticipants().addAll(participantsIds);
            return eventRepository.save(event);
        } else {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", eventId.toString()));
        }
    }

    @Override
    public List<Event> fetchAllEvents() {
        log.debug("Fetching all events");
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            throw new EventNotFoundException("No events found");
        } else {
            return events;
        }
    }

    @Override
    public Event fetchEventById(ObjectId eventId) throws EventNotFoundException {
        log.debug("Fetching event by id: {}", eventId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", eventId.toString()));
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
    public Event fetchEventBySummary(String eventSummary) throws EventNotFoundException {
        log.debug("Fetching event by summary: {}", eventSummary);
        Optional<Event> eventOptional = eventRepository.findBySummary(eventSummary);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with summary: %s does not exists.", eventSummary));
        }
    }

    @Override
    public Event fetchEventByDate(Date eventDate) throws EventNotFoundException {
        log.debug("Fetching event by date: {}", eventDate);
        Optional<Event> eventOptional = eventRepository.findByDate(eventDate);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        } else {
            throw new EventNotFoundException(String.format("Event with date: %s does not exists.", eventDate.toString()));
        }
    }

    @Override
    public List<Event> findAllEventsByParticipant(ObjectId participantId) throws EventNotFoundException {
        log.debug("Fetching all events for participant: {}", participantId);
        List<Event> resultEvents = new ArrayList<>();
        List<Event> eventList = fetchAllEvents();
        for (Event event : eventList) {
            if (event.getParticipants().contains(participantId)) {
                resultEvents.add(event);
            }
        }
        return resultEvents;
    }

    @Override
    public List<Event> findAllEventsForParticipantByDate(ObjectId participantId, Date eventDate) {
        log.debug("Finding all events for participant {} by date {}", participantId, eventDate);
        List<Event> resultEvents = new ArrayList<>();
        List<Event> eventsForParticipant = findAllEventsByParticipant(participantId);
        for (Event event : eventsForParticipant) {
            if (event.getDate().equals(eventDate)) {
                resultEvents.add(event);
            }
        }
        if (resultEvents.isEmpty()) {
            throw new EventNotFoundException(String.format("No events found for this date %s", eventDate.toString()));
        }
        else {
            return resultEvents;
        }
    }

    @Override
    public Event updateEvent(ObjectId eventId, Event event) throws EventNotFoundException {
        log.debug("Updating event with id: {} on event: {}", eventId, event);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            event.setId(eventId);
            log.debug("Update result event with event id {}, event type {}, summary {}, participants {}, at date {}",
                    event.getId(), event.getEventType(), event.getSummary(), event.getParticipants(), event.getDate());
            return eventRepository.save(event);
        } else {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists.", eventId.toString()));
        }
    }

    @Override
    public Boolean deleteEventById(ObjectId eventId) throws EventNotFoundException {
        log.debug("Deleting event with id: {} ", eventId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            eventRepository.deleteById(eventId);
            List<Event> events = eventRepository.findAll();
            if (!events.contains(eventOptional.get())) {
                return true;
            } else {
                throw new EventNotFoundException(String.format("Event with id: %s was not deleted", eventId.toString()));
            }
        } else {
            throw new EventNotFoundException(String.format("Event with id: %s does not exists", eventId.toString()));
        }
    }

    @Override
    public Event removeParticipantsFromEvent(ObjectId eventId, List<ObjectId> participantsIdsForRemoving) throws EventNotFoundException {
        log.debug("Removing participants {} from event {}", participantsIdsForRemoving, eventId);
        Event event = fetchEventById(eventId);
        Set<ObjectId> participantsIdsInEvent = event.getParticipants();
        for (ObjectId id : participantsIdsForRemoving) {
            participantsIdsInEvent.remove(id);
        }
        event.setParticipants(participantsIdsInEvent);
        return event;
    }

}
