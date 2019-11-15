package com.example.principalexampleforcontracttests.repository;

import com.example.principalexampleforcontracttests.entity.Event;
import com.example.principalexampleforcontracttests.enums.EventType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ExampleEventRepository extends MongoRepository<Event, ObjectId> {

    Optional<Event> findByEventType(EventType eventType);

    Optional<Event> findBySummary(String summary);

    Optional<Event> findByDate(Date date);
}
