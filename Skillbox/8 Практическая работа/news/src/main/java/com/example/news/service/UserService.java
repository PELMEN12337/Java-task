package com.example.news.service;

import com.example.news.model.User;
import com.example.news.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) {
        User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
