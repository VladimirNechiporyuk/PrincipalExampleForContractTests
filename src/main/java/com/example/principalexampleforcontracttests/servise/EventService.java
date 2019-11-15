package com.example.principalexampleforcontracttests.servise;

import com.example.principalexampleforcontracttests.entity.Event;
import com.example.principalexampleforcontracttests.enums.EventType;
import com.example.principalexampleforcontracttests.exceptions.EventNotFoundException;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Set;

public interface EventService {

    Event createEvent(Event event);

    Event fetchEventById(ObjectId id) throws EventNotFoundException;

    Event fetchEventByEventType(EventType eventType) throws EventNotFoundException;

    Event fetchEventBySummary(String summary) throws EventNotFoundException;

    Event fetchEventByDate(Date date) throws EventNotFoundException;

    Event updateEvent(ObjectId id, Event event) throws EventNotFoundException;

    Event addParticipants(ObjectId eventId, Set<ObjectId> participantsId);

    void deleteEventById(ObjectId id) throws EventNotFoundException;
}
