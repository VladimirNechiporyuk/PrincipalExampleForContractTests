package com.flamelab.eventnotification.servise.implementation;

import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.exceptions.UserNotFoundException;
import com.flamelab.eventnotification.repository.UserRepository;
import com.flamelab.eventnotification.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiseImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String name, Integer age) {
        log.debug("Creating user with name {} and age {}", name, age);
        User user;
        try {
            user = fetchUserByName(name);
        }
        catch (UserNotFoundException exception) {
            user = User.builder()
                    .id(ObjectId.get())
                    .name(name)
                    .age(age)
                    .build();
            return userRepository.save(user);
        }
        log.info("User with id {} already exists", user.getId());
        return user;
    }

    @Override
    public List<User> fetchAllUsers() throws UserNotFoundException {
        log.debug("Fetching all users");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        else {
            return users;
        }
    }

    @Override
    public User fetchUserById(ObjectId id) throws UserNotFoundException {
        log.debug("Fetching user by id: {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(String.format("User with id: %s does not exists.", id.toString()));
        }
    }

    @Override
    public User fetchUserByName(String name) throws UserNotFoundException {
        log.debug("Fetching user by name: {}", name);
        Optional<User> userOptional = userRepository.findByName(name);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(String.format("User with name: %s does not exists.", name));
        }
    }

    @Override
    public User fetchUserByAge(Integer age) throws UserNotFoundException {
        log.debug("Fetching user by age: {}", age);
        Optional<User> userOptional = userRepository.findByAge(age);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(String.format("User with age: %d does not exists.", age));
        }
    }

    @Override
    public User updateUser(ObjectId id, User user) throws UserNotFoundException {
        log.debug("Updating user with id: {}, on fields: {}", id, user);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }
        else {
            throw new UserNotFoundException(String.format("User with id: %s does not exists.", id.toString()));
        }
    }

    @Override
    public void deleteUserById(ObjectId id) throws UserNotFoundException {
        log.debug("Deleting user with id: {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User does not exists.");
        }
        userRepository.deleteById(id);
    }
}
