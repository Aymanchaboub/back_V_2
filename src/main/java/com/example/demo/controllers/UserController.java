package com.example.demo.controllers;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }
    

    
    @PutMapping("/users/{id}/{roleid}")
    public User updateUser(@PathVariable Long id, @RequestBody User user, @PathVariable Long roleid) {
        User existingUser = userRepository.findById(id)
                .orElseThrow();

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.getRoles().clear();
        existingUser.getRoles().add(roleRepository.getById(roleid));

        return userRepository.save(existingUser);
    }

    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow();
        
        userRepository.delete(existingUser);
        
        return ResponseEntity.ok().build();
    }
    @GetMapping("/users/role/{id}")
    public List<String> getRoleNamesByUserID(@PathVariable Long id) {
        User existingUser = userRepository.findById(id).orElseThrow();
        Set<Role> roles = existingUser.getRoles();
        List<String> roleNames = roles.stream().map(Role::getName).map(Enum::name).collect(Collectors.toList());
        return roleNames;
    }

}