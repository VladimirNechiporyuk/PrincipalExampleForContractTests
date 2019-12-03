package com.flamelab.eventnotification.servise;

import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.exceptions.UserNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    User createUser(String name, Integer age);

    List<User> fetchAllUsers();

    User fetchUserById(ObjectId id) throws UserNotFoundException;

    User fetchUserByName(String name) throws UserNotFoundException;

    User fetchUserByAge(Integer age) throws UserNotFoundException;

    User updateUser(ObjectId id, User user) throws UserNotFoundException;

    void deleteUserById(ObjectId id) throws UserNotFoundException;
}
