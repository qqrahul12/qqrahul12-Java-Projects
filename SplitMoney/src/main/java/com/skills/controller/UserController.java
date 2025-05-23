package com.skills.controller;

import com.skills.model.User;
import com.skills.model.UserRecord;
import com.skills.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true") // Allow CORS from your frontend
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable @NonNull String id) {
            UserRecord user = userService.findById(id);
            if(user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials, HttpSession session) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        UserRecord user = userService.verifyUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("user", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
