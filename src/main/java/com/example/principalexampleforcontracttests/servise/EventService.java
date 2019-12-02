package com.example.principalexampleforcontracttests.servise;

import com.example.principalexampleforcontracttests.entity.Event;
import com.example.principalexampleforcontracttests.enums.EventType;
import com.example.principalexampleforcontracttests.exceptions.EventNotFoundException;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface EventService {

    Event createEvent(EventType eventType,
                      String summary,
                      Set<ObjectId> participants,
                      Date date);

    List<Event> fetchAllEvents();

    Event fetchEventById(ObjectId id) throws EventNotFoundException;

    Event fetchEventByEventType(EventType eventType) throws EventNotFoundException;

    Event fetchEventBySummary(String summary) throws EventNotFoundException;

    Event fetchEventByDate(Date date) throws EventNotFoundException;

    Event updateEvent(ObjectId id, Event event) throws EventNotFoundException;

    Event addParticipants(ObjectId eventId, Set<ObjectId> participantsId);

    void deleteEventById(ObjectId id) throws EventNotFoundException;

    List<Event> findAllEventsByParticipant(ObjectId participantId);

    Event removeParticipantsFromEvent(ObjectId eventId, Set<ObjectId> participantsIds);
}
