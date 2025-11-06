package com.cda.camping.controller;

import com.cda.camping.model.User;
import com.cda.camping.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        userService.createUser(user);
        return new ResponseEntity<>("{message: 'User ajouté avec succès'}", headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        userService.updateUser(user);
        return new ResponseEntity<>("{message: 'User modifié avec succès'}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        userService.deleteUser(id);
        return new ResponseEntity<>("{message: 'User supprimé avec succès'}", headers, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        boolean check = userService.checkLogin(user.getLogin(), user.getPassword());
        if (check) {
            return new ResponseEntity<>("{message: 'Login avec succès'}", headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{message: 'Login ou mot de passe incorrect'}", headers, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        User registerUser = userService.registerUser(user);
        return new ResponseEntity<>(registerUser, headers, HttpStatus.CREATED);
    }
}
