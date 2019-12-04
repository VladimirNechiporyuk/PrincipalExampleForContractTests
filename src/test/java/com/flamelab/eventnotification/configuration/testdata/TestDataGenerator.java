package com.flamelab.eventnotification.configuration.testdata;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.enums.EventType;
import lombok.Data;
import org.assertj.core.util.Sets;
import org.bson.types.ObjectId;

import java.util.*;

import static com.flamelab.eventnotification.enums.EventType.WORK;

@Data
public class TestDataGenerator implements ProvidingTestData {

    private Event event;
    private Event otherEvent;
    private ObjectId eventId = new ObjectId("5c49c98d3a0f3a23cd39a720");
    private ObjectId otherEventId = new ObjectId("5a26af97857aba000719ea2e");
    private EventType eventType = WORK;
    private String eventSummary = "TestSummary";
    private Date eventDate = new Date(1577916000000L);

    private User user;
    private User otherUser;
    private ObjectId userId = new ObjectId("59b5a82c410df8000a83a1ff");
    private ObjectId otherUserId = new ObjectId("507f1f77bcf86cd799439011");
    private String userName = "TestUserName";
    private Integer userAge = 30;

    public TestDataGenerator() {
        this.event = createEvent();
        this.otherEvent = createOtherEvent();
        this.user = createUser();
        this.otherUser = createOtherUser();
    }

    @Override
    public Set<ObjectId> getEventsIds() {
        Set<ObjectId> eventsIds = new HashSet<>();
        eventsIds.add(eventId);
        eventsIds.add(otherEventId);
        return eventsIds;
    }

    @Override
    public List<Event> getEventList() {
        return Arrays.asList(createEvent(), createOtherEvent());
    }

    @Override
    public Set<ObjectId> getUsersIds() {
        Set<ObjectId> usersIds = new HashSet<>();
        usersIds.add(userId);
        usersIds.add(otherUserId);
        return usersIds;
    }

    @Override
    public List<User> getUserList() {
        return Arrays.asList(createUser(), createOtherUser());
    }

    private Event createEvent() {
        return Event.builder()
                .id(eventId)
                .eventType(eventType)
                .summary(eventSummary)
                .participants(getUsersIds())
                .date(eventDate)
                .build();
    }

    private Event createOtherEvent() {
        return Event.builder()
                .id(eventId)
                .eventType(eventType)
                .summary(String.format("Other%s", eventSummary))
                .participants(getUsersIds())
                .date(eventDate)
                .build();
    }

    private User createUser() {
        return User.builder()
                .id(otherUserId)
                .name(userName)
                .age(userAge)
                .build();
    }

    private User createOtherUser() {
        return User.builder()
                .id(otherEventId)
                .name(String.format("Other%s", userName))
                .age(1+userAge)
                .build();
    }
}
