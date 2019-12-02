package com.example.principalexampleforcontracttests.controller;

import com.example.principalexampleforcontracttests.entity.User;
import com.example.principalexampleforcontracttests.servise.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
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
