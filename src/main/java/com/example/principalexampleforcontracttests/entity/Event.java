package com.example.principalexampleforcontracttests.entity;

import com.example.principalexampleforcontracttests.enums.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.Set;

@Data
@Document(collection = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private ObjectId id;

    @JsonProperty("eventType")
    private EventType eventType;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("participants")
    private Set<ObjectId> participants;

    @JsonProperty("date")
    private Date date;
}
