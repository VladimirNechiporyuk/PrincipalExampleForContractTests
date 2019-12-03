package com.flamelab.eventnotification.repository;

import com.flamelab.eventnotification.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByName(String name);

    Optional<User> findByAge(Integer age);
}
