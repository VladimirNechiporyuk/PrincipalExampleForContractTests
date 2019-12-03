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

import static org.junit.Assert.assertNotNull;

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
    }

    @Test
    public void fetchAllEvents() {

    }

    @Test
    public void fetchEventById() {
    }

    @Test
    public void fetchEventByEventType() {
    }

    @Test
    public void fetchEventBySummary() {
    }

    @Test
    public void fetchEventByDate() {
    }

    @Test
    public void updateEvent() {
    }

    @Test
    public void addParticipants() {
    }

    @Test
    public void deleteEventById() {
    }

    @Test
    public void findAllEventsByParticipant() {
    }

    @Test
    public void removeParticipantsFromEvent() {
    }
}