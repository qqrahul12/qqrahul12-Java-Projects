package com.skills.service;

import com.skills.model.User;
import com.skills.model.UserRecord;
import com.skills.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRecord findById(String id) throws EntityNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        return UserRecord.fromUser(user.get());
    }

    public UserRecord verifyUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return UserRecord.fromUser(user);
        }
        return null;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
