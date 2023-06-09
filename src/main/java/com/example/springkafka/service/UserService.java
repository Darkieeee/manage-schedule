package com.example.springkafka.service;

import com.example.springkafka.entity.User;
import com.example.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void create(String username, String password, String retypePass) throws Exception {
        User savedUser = userRepository.findByUsername(username).orElse(null);
        if(savedUser != null) {
            System.out.println("I am here");
            throw new Exception("Username has been registered");
        }
        else {
            if(password.isBlank() || retypePass.isBlank()) {
                throw new Exception("Password or retype password mustn't be blank");
            }
            else if(!password.equals(retypePass)){
                throw new Exception("Password wasn't matched");
            }
            else {
                User user = new User();
                user.setUsername(username);
                user.setPassword(bCryptPasswordEncoder.encode(password));
                userRepository.save(user);
            }
        }
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

    public boolean has(String username) {
        return userRepository.existsByUsername(username);
    }

}
