package com.flamelab.eventnotification.configuration.testdata;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.enums.EventType;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.*;

@Data
public class TestDataGenerator implements ProvidingTestData {

    private Event event;
    private User user;

    private ObjectId eventId = new ObjectId("5c49c98d3a0f3a23cd39a720");
    private ObjectId otherEventId = new ObjectId("5a26af97857aba000719ea2e");
    private ObjectId userId = new ObjectId("59b5a82c410df8000a83a1ff");
    private ObjectId otherUserId = new ObjectId("507f1f77bcf86cd799439011");


    public TestDataGenerator() {
        this.event = createEvent();
        this.user = createUser();
    }

    @Override
    public List<Event> getEventList() {
        return Arrays.asList(createEvent(), createOtherEvent());
    }

    @Override
    public List<User> getUserList() {
        return Arrays.asList(createUser(), createOtherUser());
    }

    private Event createEvent() {
        return Event.builder()
                .id(eventId)
                .eventType(EventType.WORK)
                .summary("TestSummary")
                .participants(Collections.singleton(userId))
                .date(new Date(1577829600000L))
                .build();
    }

    private Event createOtherEvent() {
        return Event.builder()
                .id(eventId)
                .eventType(EventType.WORK)
                .summary("TestSummary")
                .participants(Collections.singleton(userId))
                .date(new Date(1577916000000L))
                .build();
    }

    private User createUser() {
        return User.builder()
                .id(otherUserId)
                .name("TestUserName")
                .age(30)
                .build();
    }

    private User createOtherUser() {
        return User.builder()
                .id(otherEventId)
                .name("TestOtherUserName")
                .age(31)
                .build();
    }
}
