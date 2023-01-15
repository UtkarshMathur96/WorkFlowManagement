package com.example.demo.controllers;

import com.example.demo.constants.Constants;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = Constants.USER_PATH)
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = Constants.ADD)
    public @ResponseBody
    String addUser(@RequestParam String name
            , @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "User Created";
    }

    @GetMapping(path = Constants.ALL)
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
