package com.example.principalexampleforcontracttests.servise.implementation;

import com.example.principalexampleforcontracttests.entity.User;
import com.example.principalexampleforcontracttests.exceptions.UserNotFoundException;
import com.example.principalexampleforcontracttests.repository.ExampleUserRepository;
import com.example.principalexampleforcontracttests.servise.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiseImpl implements UserService {

    private ExampleUserRepository userRepository;

    @Autowired
    public UserServiseImpl(ExampleUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User fetchUserById(ObjectId id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(String.format("User with id: %s does not exists.", id.toString()));
        }
    }

    @Override
    public User fetchUserByName(String name) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByName(name);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(String.format("User with name: %s does not exists.", name));
        }
    }

    @Override
    public User fetchUserByAge(Integer age) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByAge(age);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(String.format("User with age: %d does not exists.", age));
        }
    }

    @Override
    public User updateUser(ObjectId id, User user) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException(String.format("User with id: %s does not exists.", id.toString()));
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(ObjectId id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User does not exists.");
        }
        userRepository.deleteById(id);
    }
}
