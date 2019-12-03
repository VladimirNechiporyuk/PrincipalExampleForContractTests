package com.flamelab.eventnotification.configuration.testdata;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.entity.User;

import java.util.List;

public interface ProvidingTestData {

    Event getEvent();

    User getUser();

    List<Event> getEventList();

    List<User> getUserList();
}
