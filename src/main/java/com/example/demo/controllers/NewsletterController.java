package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class NewsletterController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/newsletter/{email}")
    public boolean isEmailExists(@PathVariable String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

}