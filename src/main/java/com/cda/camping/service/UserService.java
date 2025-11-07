package com.cda.camping.service;

import com.cda.camping.model.User;
import com.cda.camping.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public void updateUser(User user) {
                // Si un nouveau mot de passe est fourni, on l'encode.
        // Sinon, on garde l'ancien pour ne pas l'écraser.
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            User currentUser = userRepository.findById(user.getId());
            if (currentUser != null) {
                user.setPassword(currentUser.getPassword());
            }
        }
        userRepository.update(user);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean checkLogin(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return user;
    }
}

