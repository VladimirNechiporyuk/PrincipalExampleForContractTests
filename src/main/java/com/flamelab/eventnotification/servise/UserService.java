package com.flamelab.eventnotification.servise;

import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.exceptions.UserNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;

public interface UserService {

    User createUser(String name, Integer age);

    List<User> fetchAllUsers();

    User fetchUserById(ObjectId id) throws UserNotFoundException;

    User fetchUserByName(String name) throws UserNotFoundException;

    User fetchUserByAge(Integer age) throws UserNotFoundException;

    Set<User> fetchUsersByIds(Set<ObjectId> users);

    User updateUser(ObjectId id, User user) throws UserNotFoundException;

    void deleteUserById(ObjectId id) throws UserNotFoundException;
}
