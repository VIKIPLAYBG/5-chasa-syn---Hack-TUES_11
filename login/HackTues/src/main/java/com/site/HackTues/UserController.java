package com.site.HackTues;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @PostMapping("/register")
    public String userRegistration(@ModelAttribute User user, Model model) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userInserted = userRepo.save(user);
            model.addAttribute("user", userInserted);
            return "welcome";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user, Model model) {
        Optional<User> userOptional = userRepo.findByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();
            if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                return "index";
            } else {
                return "Invalid credentials";
            }
        } else {
            return "User not found";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
