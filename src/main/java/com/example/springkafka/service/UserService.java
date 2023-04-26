package com.example.springkafka.service;

import com.example.springkafka.entity.User;
import com.example.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User updateUser(User user) {
        User userFound = getUser(user.getId());

        userFound.setUsername(user.getUsername());
        userFound.setPassword(user.getPassword());

        return userRepository.save(userFound);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
