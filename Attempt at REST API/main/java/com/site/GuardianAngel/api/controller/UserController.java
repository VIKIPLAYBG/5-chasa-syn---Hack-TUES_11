package com.site.GuardianAngel.api.controller;

import com.site.GuardianAngel.api.model.User;
import com.site.GuardianAngel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users") // IDK if this is the right path, subject to change
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all") // IDK if this is the right path, subject to change
    public String getUsers(){
        return "users";
    }

    @GetMapping(path = "/user")
    public User getUser(Long id) {
        Optional<User> user = userService.getUser(id);
        // Commented code should be doing the same as the line below
        if(user.isPresent()) {
            User user1 = user.get();
            System.out.println("Hello " + user1.getUsername()); // Remove this line later
            return user1;
        }
        return null;
//        return user.orElse(null);
    }
}
