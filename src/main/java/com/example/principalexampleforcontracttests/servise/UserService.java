package com.example.principalexampleforcontracttests.servise;

import com.example.principalexampleforcontracttests.entity.User;
import com.example.principalexampleforcontracttests.exceptions.UserNotFoundException;
import org.bson.types.ObjectId;

public interface UserService {

    User createUser(User user);

    User fetchUserById(ObjectId id) throws UserNotFoundException;

    User fetchUserByName(String name) throws UserNotFoundException;

    User fetchUserByAge(Integer age) throws UserNotFoundException;

    User updateUser(ObjectId id, User user) throws UserNotFoundException;

    void deleteUserById(ObjectId id) throws UserNotFoundException;
}
