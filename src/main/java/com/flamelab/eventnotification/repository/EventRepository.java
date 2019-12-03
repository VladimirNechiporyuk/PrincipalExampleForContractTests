package com.flamelab.eventnotification.repository;

import com.flamelab.eventnotification.entity.Event;
import com.flamelab.eventnotification.enums.EventType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventRepository extends MongoRepository<Event, ObjectId> {

    Optional<Event> findByEventType(EventType eventType);

    Optional<Event> findBySummary(String summary);

    Optional<Event> findByDate(Date date);
}
