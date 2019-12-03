package com.flamelab.eventnotification.servise;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.enums.EventType;
import com.flamelab.eventnotification.exceptions.EventNotFoundException;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface EventService {

    Event createEvent(EventType eventType, String summary, Set<ObjectId> participants, Date date);

    List<Event> fetchAllEvents();

    Event fetchEventById(ObjectId eventId) throws EventNotFoundException;

    Event fetchEventByEventType(EventType eventType) throws EventNotFoundException;

    Event fetchEventBySummary(String eventSummary) throws EventNotFoundException;

    Event fetchEventByDate(Date eventDate) throws EventNotFoundException;

    Event updateEvent(ObjectId eventId, Event event) throws EventNotFoundException;

    Event addParticipants(ObjectId eventId, Set<ObjectId> participantsId);

    List<Event> findAllEventsByParticipant(ObjectId participantId);

    List<Event> findAllEventsForParticipantByDate(ObjectId participantId, Date date);

    void deleteEventById(ObjectId id) throws EventNotFoundException;

    Event removeParticipantsFromEvent(ObjectId eventId, Set<ObjectId> participantsIds);
}
