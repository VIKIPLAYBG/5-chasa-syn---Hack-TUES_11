package com.site.HackTues;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class indexController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model) {
        System.out.println(user.toString());
        model.addAttribute("firstname", user.getFname());
        model.addAttribute("tname", user.getFname());
        return "welcome.html";
    }
}
