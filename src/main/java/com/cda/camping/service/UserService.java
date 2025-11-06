package com.cda.camping.service;

import com.cda.camping.model.User;
import com.cda.camping.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
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
        return user.getPassword().equals(password);
    }

    public User registerUser(User user) {
        userRepository.save(user);
        return user;
    }
}

