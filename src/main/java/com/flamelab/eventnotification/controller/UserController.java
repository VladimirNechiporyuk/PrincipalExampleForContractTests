package com.flamelab.eventnotification.controller;

import com.flamelab.eventnotification.entity.User;
import com.flamelab.eventnotification.servise.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestParam String name,
                           @RequestParam Integer age) {
        return userService.createUser(name, age);
    }

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.fetchAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") ObjectId id) {
        return userService.fetchUserById(id);
    }

    @GetMapping("/name")
    public User findUserByName(@RequestParam("name") String name) {
        return userService.fetchUserByName(name);
    }

    @GetMapping("/age")
    public User findUserByAge(@RequestParam("age") Integer age) {
        return userService.fetchUserByAge(age);
    }

    @PutMapping
    public User updateUser(@RequestParam("id") ObjectId id,
                           User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping
    public void deleteUserById(@RequestParam("id") ObjectId id) {
        userService.deleteUserById(id);
    }

}
