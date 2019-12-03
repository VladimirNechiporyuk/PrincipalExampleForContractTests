package com.flamelab.eventnotification.controller;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.enums.EventType;
import com.flamelab.eventnotification.servise.EventService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestParam EventType eventType,
                             @RequestParam String summary,
                             @RequestParam Set<ObjectId> participants,
                             @RequestParam Date date) {
        return eventService.createEvent(eventType, summary, participants, date);
    }

    @PostMapping("/addParticipants")
    public Event addParticipants(@RequestParam("eventId") ObjectId eventId,
                                 @RequestBody Set<ObjectId> participantsIds) {
        return eventService.addParticipants(eventId, participantsIds);
    }

    @GetMapping("/all")
    public List<Event> findAllEvents() {
        return eventService.fetchAllEvents();
    }

    @GetMapping("/{id}")
    public Event findEventById(@PathVariable("id") ObjectId id) {
        return eventService.fetchEventById(id);
    }

    @GetMapping("/eventType")
    public Event findEventByEventType(@RequestParam("eventType") EventType eventType) {
        return eventService.fetchEventByEventType(eventType);
    }

    @GetMapping("/summary")
    public Event findEventBySummary(@RequestParam("summary") String summary) {
        return eventService.fetchEventBySummary(summary);
    }

    @GetMapping("/date")
    public Event findEventByDate(@RequestParam("date") Date date) {
        return eventService.fetchEventByDate(date);
    }

    @GetMapping("/eventsForParticipant")
    public List<Event> findAllEventsForParticipant(ObjectId participantId) {
        return eventService.findAllEventsByParticipant(participantId);
    }

    @PutMapping
    public Event updateEvent(@RequestParam("id") ObjectId id,
                             @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @PutMapping("/removeParticipants")
    public Event removeParticipants(@RequestParam("eventId") ObjectId eventId,
                                    @RequestParam("participantsIds") Set<ObjectId> participantsIds) {
        return eventService.removeParticipantsFromEvent(eventId, participantsIds);
    }

    @DeleteMapping
    public void deleteEvent(@RequestParam("id") ObjectId id) {
        eventService.deleteEventById(id);
    }
}
