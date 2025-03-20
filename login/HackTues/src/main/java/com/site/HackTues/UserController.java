package com.site.HackTues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model) {
        try {
            User userInserted = userRepo.save(user);
            model.addAttribute("user", userInserted);
            return "welcome";
        } catch (Exception e) {
            return "error";
        }
    }
}
