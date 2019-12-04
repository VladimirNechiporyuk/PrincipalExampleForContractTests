package com.flamelab.eventnotification.servise.implementation;

import com.flamelab.eventnotification.configuration.TestConfig;
import com.flamelab.eventnotification.configuration.mocks.MocksForEventService;
import com.flamelab.eventnotification.configuration.testdata.ProvidingTestData;
import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.servise.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class, MocksForEventService.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EventServiceImplTest {

    @Autowired
    private EventService eventService;
    @Autowired
    private ProvidingTestData testDataProvider;
    @Autowired
    private MocksForEventService mocksForService;

    @Before
    public void settingUp() {
        mocksForService.mockResponses();
    }

    @Test
    public void createEvent() {
        Event newEvent = testDataProvider.getEvent();
        Event resultEvent = eventService.createEvent(
                newEvent.getEventType(),
                newEvent.getSummary(),
                newEvent.getParticipants(),
                newEvent.getDate());

        assertNotNull(resultEvent);
        assertNotEquals(resultEvent.getId(), newEvent.getId());
        assertEquals(resultEvent.getEventType(), newEvent.getEventType());
        assertEquals(resultEvent.getSummary(), newEvent.getSummary());
        assertEquals(resultEvent.getParticipants(), newEvent.getParticipants());
        assertEquals(resultEvent.getDate(), newEvent.getDate());
    }

    @Test
    public void addParticipants() {
        Event event = eventService.addParticipants(testDataProvider.getEventId(), testDataProvider.getUsersIds());
        assertEquals(event.getParticipants(), testDataProvider.getUsersIds());
    }

    @Test
    public void fetchAllEvents() {
        assertEquals(eventService.fetchAllEvents(), testDataProvider.getEventList());
    }

    @Test
    public void fetchEventById() {
        assertEquals(eventService.fetchEventById(testDataProvider.getEventId()), testDataProvider.getEvent());
    }

    @Test
    public void fetchEventByEventType() {
        assertEquals(eventService.fetchEventByEventType(testDataProvider.getEventType()), testDataProvider.getEvent());
    }

    @Test
    public void fetchEventBySummary() {
        assertEquals(eventService.fetchEventBySummary(testDataProvider.getEventSummary()), testDataProvider.getEvent());
    }

    @Test
    public void fetchEventByDate() {
        assertEquals(eventService.fetchEventByDate(testDataProvider.getEventDate()), testDataProvider.getEvent());
    }

    @Test
    public void findAllEventsByParticipant() {
        List<Event> events = eventService.findAllEventsByParticipant(testDataProvider.getUserId());

    }

    @Test
    public void updateEvent() {
        Event initialEvent = testDataProvider.getEvent();
        Event eventWithChanges = testDataProvider.getOtherEvent();
        eventWithChanges.setId(initialEvent.getId());
        assertEquals(eventService.updateEvent(initialEvent.getId(), eventWithChanges), eventWithChanges);
    }

    @Test
    public void deleteEventById() {

    }

    @Test
    public void removeParticipantsFromEvent() {

    }
}