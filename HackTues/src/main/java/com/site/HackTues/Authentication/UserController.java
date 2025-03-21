package com.site.HackTues.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenBlacklistService tokenBlacklistService;

    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder, TokenBlacklistService tokenBlacklistService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenBlacklistService = tokenBlacklistService;
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String token,
                                         HttpServletRequest request, HttpServletResponse response) {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(403).body("You are not logged in!");
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }

        SecurityContextHolder.clearContext();
        request.getSession().invalidate();

        return ResponseEntity.ok("Logged out successfully!");
    }
}
