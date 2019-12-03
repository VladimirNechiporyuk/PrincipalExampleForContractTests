package com.flamelab.eventnotification.configuration.mocks;

import com.flamelab.eventnotification.configuration.testdata.ProvidingTestData;
import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@Component
public class MocksForEventService implements MocksForService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ProvidingTestData testDataProvider;

    @Override
    public void mockResponses() {
        mockSavingEvent();
    }

    private void mockSavingEvent() {
        Event event = testDataProvider.getEvent();
        when(eventRepository.save(isA(Event.class))).thenReturn(event);
    }

    private void mockFindAllEvents() {
        when(eventRepository.findAll()).thenReturn(testDataProvider.getEventList());
    }

    private void mockFindEventById() {
        when(eventRepository.findById(testDataProvider.getEvent().getId()))
                .thenReturn(Optional.of(testDataProvider.getEvent()));
    }

    private void mockFindByEventType() {
        when(eventRepository.findByEventType(testDataProvider.getEvent().getEventType()))
                .thenReturn(Optional.of(testDataProvider.getEvent()));
    }

    private void mockFindBySummary() {
        when(eventRepository.findBySummary(testDataProvider.getEvent().getSummary()))
                .thenReturn(Optional.of(testDataProvider.getEvent()));
    }

    private void mockFindByDate() {
        when(eventRepository.findByDate(testDataProvider.getEvent().getDate()))
                .thenReturn(Optional.of(testDataProvider.getEvent()));
    }



}
