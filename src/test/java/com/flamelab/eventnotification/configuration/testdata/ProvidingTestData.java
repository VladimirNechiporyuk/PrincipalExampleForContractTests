package com.flamelab.eventnotification.configuration.testdata;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.enums.EventType;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ProvidingTestData {

    Event getEvent();
    Event getOtherEvent();
    ObjectId getEventId();
    EventType getEventType();
    String getEventSummary();
    Date getEventDate();
    Set<ObjectId> getEventsIds();
    List<Event> getEventList();

    User getUser();
    User getOtherUser();
    ObjectId getUserId();
    String getUserName();
    Integer getUserAge();
    Set<ObjectId> getUsersIds();
    List<User> getUserList();
}
