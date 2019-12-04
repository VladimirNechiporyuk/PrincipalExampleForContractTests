package com.flamelab.eventnotification.controller;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.entity.User;
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

    @GetMapping("/allEventsForParticipant")
    public List<Event> findAllEventsForParticipant(ObjectId participantId) {
        return eventService.findAllEventsByParticipant(participantId);
    }

    @GetMapping("/eventsForParticipantByDate")
    public List<Event> findAllEventsForParticipantByDate(@RequestParam("date") ObjectId participantId,
                                                         @RequestParam("date") Date date) {
        return eventService.findAllEventsForParticipantByDate(participantId, date);
    }

    @PutMapping
    public Event updateEvent(@RequestParam("id") ObjectId id,
                             @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @PutMapping("/removeParticipants")
    public Event removeParticipants(@RequestParam("eventId") ObjectId eventId,
                                    @RequestParam("participantsIds") List<ObjectId> participantsIds) {
        return eventService.removeParticipantsFromEvent(eventId, participantsIds);
    }

    @DeleteMapping
    public Boolean deleteEvent(@RequestParam("id") ObjectId id) {
        return eventService.deleteEventById(id);
    }
}
