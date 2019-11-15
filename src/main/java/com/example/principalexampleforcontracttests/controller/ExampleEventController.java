package com.example.principalexampleforcontracttests.controller;

import com.example.principalexampleforcontracttests.entity.Event;
import com.example.principalexampleforcontracttests.enums.EventType;
import com.example.principalexampleforcontracttests.servise.EventService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/events")
public class ExampleEventController {

    private EventService eventService;

    @Autowired
    public ExampleEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
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

    @PutMapping
    public Event updateEvent(@RequestParam("id") ObjectId id,
                             @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping
    public void deleteEvent(@RequestParam("id") ObjectId id) {
        eventService.deleteEventById(id);
    }
}
